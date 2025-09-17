package site.lets_go_support.send_files_to_server

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.protobuf.GeneratedMessageLite
import io.grpc.*
import io.grpc.android.AndroidChannelBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.Channel
import retrieve_server_load.RetrieveServerLoad
import retrieve_server_load.RetrieveServerLoadServiceGrpc
import site.lets_go_support.send_files_to_server.Clients.SendPicturesForTesting
import site.lets_go_support.send_files_to_server.databinding.FragmentEnterInfoBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.net.URI

class EnterInfo : Fragment() {

    private var _binding: FragmentEnterInfoBinding? = null
    private val binding get() = _binding!!

    //val createdChannel: ManagedChannel = AndroidChannelBuilder.forAddress("{redacted}", 50051)
    var createdChannel: ManagedChannel = AndroidChannelBuilder.forAddress("10.0.2.2", 50052)
        .usePlaintext()
        .context(activity?.applicationContext)
        .build()

    class MultiAddressNameResolverFactory internal constructor(vararg addresses: SocketAddress?) :
        NameResolverProvider() {
        private val addresses: List<EquivalentAddressGroup>
        override fun newNameResolver(notUsedUri: URI?, args: NameResolver.Args?): NameResolver {

            return object : NameResolver() {
                init {
                    Log.i("retrieveServerTime", "NameResolver init")
                }

                override fun getServiceAuthority(): String {
                    Log.i("retrieveServerTime", "NameResolver getServiceAuthority()")
                    return "multiaddress"
                }

                override fun start(listener: Listener2) {
                    Log.i("retrieveServerTime", "NameResolver start called addresses: $addresses")
                    listener.onResult(
                        ResolutionResult.newBuilder().setAddresses(addresses)
                            .setAttributes(Attributes.EMPTY).build()
                    )
                }

                override fun shutdown() {
                    Log.i("retrieveServerTime", "NameResolver shutdown()")
                }
            }
        }

        override fun getDefaultScheme(): String {
            Log.i("retrieveServerTime", "NameResolverProvider getDefaultScheme()")
            return "multiaddress";
        }

        override fun isAvailable(): Boolean {
            Log.i("retrieveServerTime", "NameResolverProvider isAvailable()")
            return true
        }

        override fun priority(): Int {
            Log.i("retrieveServerTime", "NameResolverProvider priority()")
            return 10
        }

        init {

            Log.i("retrieveServerTime", "NameResolverProvider init()")

            val mutableAddresses = mutableListOf<EquivalentAddressGroup>()

            for(addr in addresses) {
                mutableAddresses.add(EquivalentAddressGroup(addr))
            }

            this.addresses = mutableAddresses
        }
    }

    private fun <U : GeneratedMessageLite<U, V>?, V : GeneratedMessageLite.Builder<U, V>?> grpcFunctionCallTemplate(
        messageLite: GeneratedMessageLite<U, V>
    ) {
        Log.i("messageLigt", "default: ${messageLite.defaultInstanceForType}")
    }

    class HelloWorld {
        var myVar = ""
    }

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEnterInfoBinding.inflate(inflater, container, false)

        val separatorRegex = Regex(",")
        val list = separatorRegex.split("").toMutableList()

        Log.i("regexList", "$list ${list.size}")

        val nameResolverFactory: NameResolverProvider = MultiAddressNameResolverFactory(
            InetSocketAddress("{redacted}", 50051),
            InetSocketAddress("{redacted}", 50051),
            InetSocketAddress("{redacted}", 50051),
            InetSocketAddress("{redacted}", 50051),
            InetSocketAddress("{redacted}", 50051),
            InetSocketAddress("{redacted}", 50051),
        )

        NameResolverRegistry.getDefaultRegistry().register(nameResolverFactory)

        binding.continueButton.setOnClickListener {

            /*CoroutineScope(IO).launch {
                var bigger = ""
                //               2147483000
                for (i in 0 until 2147483 / (100 * 100)) {
                    bigger += 'a'
                }

                var biggest = ""
                for (i in 0 until 100) {
                    biggest += bigger
                }

                var not_overflow = ""

                for (i in 0 until 100) {
                    //for(i in 0 until 2147483000) {
                    not_overflow += biggest
                }

                val request = RetrieveServerLoad.RetrieveServerLoadRequest.newBuilder()
                    .setRequestNumClients(true)
                    .setHelloOverflow(ByteString.copyFromUtf8(not_overflow))
                    .build()

                val jobs = mutableListOf<Job>()

//                val channel: ManagedChannel = AndroidChannelBuilder.forAddress("{redacted}", 50051)
                val channel: ManagedChannel = AndroidChannelBuilder.forTarget("multiaddress")
                    .usePlaintext()
                    .context(activity?.applicationContext)
                    .executor(Executors.newCachedThreadPool())
                    .offloadExecutor(Executors.newCachedThreadPool())
                    .build()

                Log.i("retrieveServerTime", "Starting Load Balancing Loop")

                for (i in 0 until 3) {
                    jobs.add(
                        CoroutineScope(IO).launch {
                            loadBalanceServer(request, channel)
                        }
                    )
                }

                for (j in jobs) {
                    j.join()
                }
            }*/

            uploadfiles()

            ////TODO: make sure to retry connection, if the connection initially fails the onError callback is never called(wait-for-ready?)
            //BiDiTestClient.requestObserver?.onNext(
            //    BiDiStreamTest.BiDiRequest.newBuilder()
            //        .setRequest(enterFirstEditText.text.toString() + enterLastEditText.text.toString())
            //        .build()
            //)
            //Log.i("startBiDiTest", "state : ${channel.getState(false)}")

//            uploadfiles()
//            viewModel.setLiveData()

//            CoroutineScope(IO).launch {
//                runCoroutine()
//            }
//            Log.i("scopeCheck", "After launch coroutine")
//            intArray.add(9)
//            intArray.add(8)
//            intArray.add(7)
//            intArray.add(6)

//            FindMatchesClient.runClient();
//            intArray[0] = 9
//            intArray[1] = 8
//            intArray[2] = 7
//            intArray[3] = 6

//            for(i in intArray){
//                Log.i("EnterInfo", "Value: $i")
//            }
//            //findNavController().navigate(R.id.action_enterInfo_to_getInfo)
//            Log.i("LogErrorClient", "Running Client")
//
//            val bitmap = imageView2.drawable.toBitmap()
//
//            imageView2.setImageDrawable(null)
//
//            CoroutineScope(IO).launch{
//                runClientStuff(bitmap)
//            }

        }

        binding.enterButton.setOnClickListener {

//            grpcFunctionCallTemplate<MathReply, MathReply.Builder>(MathReply.newBuilder().build())

//            thingies[0].notifyWhenStateChanged(ConnectivityState.IDLE) {
//                Log.i("termination", "notifyWhenStateChanged ConnectivityState.SHUTDOWN")
//            }
//            Log.i("termination", "thingies[0].getState ${thingies[0].getState(false)}")
//            Log.i("termination", "awaitTermination started")
//            thingies[0].shutdown()//.awaitTermination(5000L, TimeUnit.MILLISECONDS)

//            Log.i("termination", "thingies[0].getState ${thingies[0].getState(false)}")
            //startBiDiTest(channel, streamResponse)

//            if(imageView2.drawable == null) {
//
//                val beforeTimestamp = System.currentTimeMillis()
//                Log.i("EnterButton", "daPath $daPath")
//                val f = File(daPath)
//
//                val afterTimestamp = System.currentTimeMillis()
//                Log.i("EnterButton", "Time: ${afterTimestamp - beforeTimestamp}")
//
//                val returnBitmap =
//                    BitmapFactory.decodeByteArray(f.readBytes(), 0, f.readBytes().size)
//
//                CoroutineScope(IO).launch {
//                    runClientStuff(returnBitmap)
//                }
//
//                imageView2.setImageBitmap(returnBitmap)
//
//                f.delete()
//
//            } else {
//
//                val bitmap = imageView2.drawable.toBitmap()
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
//                val imageInBytes = baos.toByteArray()
//
//                val f = File(requireActivity().applicationContext.filesDir, "foo.txt")
//
//                f.writeBytes(imageInBytes)
//                daPath = f.path
//                Log.i("EnterButton", "File Name: ${f.name} Path: ${f.path}")
//
//                imageView2.setImageDrawable(null)
//
//            }
//
//            val files: Array<String> = requireContext().fileList()
//
//            if (files.isEmpty())
//                Log.i("EnterButton", "No Files Exist")
//            else
//                for (s in files)
//                    Log.i("EnterButton", "File Name: $s")

//            val firstName = myViewGroup.enterFirstEditText.text.toString()
//            val lastName = myViewGroup.enterLastEditText.text.toString()
//            val phoneNumber = "+" + myViewGroup.enterPhoneEditText.text.toString()
//
//            val argsList: List<String> = listOf(firstName, lastName, phoneNumber)
//
//            val result = StoreDataClient.runClient(argsList)
//
//            myViewGroup.displayEnterInfo.append(result + '\n')


//            val client = LogErrorClient()
//            client.logError()
//
//            Log.i("LogErrorClient", "Clicked")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        streamResponse = object : StreamObserver<BiDiResponse> {
//
//            var firstRun = true
//
//            override fun onNext(value: BiDiResponse) {
//                CoroutineScope(Main).launch {
//
//                    if(firstRun) {
//                        firstRun = false
//                    }
//
//                    enterTitle.text = value.response
//                }
//            }
//
//            override fun onError(t: Throwable) {
//                CoroutineScope(Main).launch {
//                    enterTitle.text = "error: ${t.localizedMessage}"
//                }
//                Log.i("startBiDiTest", "onError")
//                BiDiTestClient.requestObserver?.onNext(
//                    BiDiStreamTest.BiDiRequest.newBuilder()
//                        .setRequest(enterFirstEditText.text.toString() + enterLastEditText.text.toString())
//                        .build()
//                )
//                Log.i("startBiDiTest", "state : ${createdChannel.getState(false)}")
//                startBiDiTest(createdChannel, streamResponse)
//            }
//
//            override fun onCompleted() {
//                CoroutineScope(Main).launch {
//                    enterTitle.text = "onCompleted Ran"
//                }
//                Log.i("startBiDiTest", "onCompleted")
//                startBiDiTest(createdChannel, streamResponse)
//            }
//        }
//
//        CoroutineScope(IO).launch {
//            Log.i("chatStream", startBiDiTest(createdChannel, streamResponse))
//        }
    }

    suspend fun funcTwo() {
        delay(1000)
    }

    private suspend fun runCoroutine() {
        withContext(IO) {
            delay(2000)
            runCoroutineTwo()
        }
    }

    private fun runCoroutineTwo() {
        Log.i("scopeCheck", "Finished Coroutine")
    }

    private suspend fun runClientStuff(passedBitmap: Bitmap) {
        withContext(IO) {

            val timeBefore = System.currentTimeMillis()

            val bitmap2 = passedBitmap
            val baos2 = ByteArrayOutputStream()
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2)
            val imageInBytes2 = baos2.toByteArray()

            Log.i("SizeOfFile", "Initial Size Bytes: ${imageInBytes2.size}")
            Log.i("SizeOfFile", "Initial Size Kbs: ${imageInBytes2.size / 1000}")
            Log.i("SizeOfFile", "Initial Size Mbs: ${imageInBytes2.size / 1000000}")

            val bitmap = passedBitmap

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)

            try {
                baos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val imageInByte = baos.toByteArray()

            Log.i("SizeOfFile", "B4 send Bytes: ${imageInByte.size}")
            Log.i("SizeOfFile", "B4 send Kbs: ${imageInByte.size / 1000}")
            Log.i("SizeOfFile", "B4 send Mbs: ${imageInByte.size / 1000000}")

            val client =
                SendPicturesForTesting()
            val returnByteArray = client.runClient(imageInByte, imageInByte, createdChannel)

            Log.i("SizeOfFile", "After send Bytes: ${returnByteArray.size}")
            Log.i("SizeOfFile", "After send Kbs: ${returnByteArray.size / 1000}")
            Log.i("SizeOfFile", "After send Mbs: ${returnByteArray.size / 1000000}")

            val returnBitmap =
                BitmapFactory.decodeByteArray(returnByteArray, 0, returnByteArray.size)

            withContext(Main) {
                binding.imageView2.setImageBitmap(returnBitmap)
            }

            val timeAfter = System.currentTimeMillis()

            Log.i("SizeOfFile", "Finished Running Client, Time(ms): ${timeAfter - timeBefore}")
        }
    }

    private fun uploadfiles() {
        CoroutineScope(IO).launch {
            convertResourceIDToByteArray(R.drawable.ad_deogam)
            convertResourceIDToByteArray(R.drawable.adam_winger)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_0)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_1)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_2)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_3)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_4)
            convertResourceIDToByteArray(R.drawable.alexander_krivitski_5)
            convertResourceIDToByteArray(R.drawable.alfaz_sayed_0)
            convertResourceIDToByteArray(R.drawable.alfaz_sayed_1)
            convertResourceIDToByteArray(R.drawable.ali_kazal)
            convertResourceIDToByteArray(R.drawable.allgo_0)
            convertResourceIDToByteArray(R.drawable.allgo_1)
            convertResourceIDToByteArray(R.drawable.amanda_vick)
            convertResourceIDToByteArray(R.drawable.anastasia_emelyanova)
            convertResourceIDToByteArray(R.drawable.anastasia_vityukov_0)
            convertResourceIDToByteArray(R.drawable.anastasia_vityukov_1)
            convertResourceIDToByteArray(R.drawable.anastasia_vityukov_2)
            convertResourceIDToByteArray(R.drawable.andreea_0)
            convertResourceIDToByteArray(R.drawable.andrey_zvyagintsev)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_0)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_1)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_2)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_3)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_4)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_5)
            convertResourceIDToByteArray(R.drawable.andriyko_podilnyk_6)
            convertResourceIDToByteArray(R.drawable.anna_earl)
            convertResourceIDToByteArray(R.drawable.annie_spratt)
            convertResourceIDToByteArray(R.drawable.antoine_beauvillain)
            convertResourceIDToByteArray(R.drawable.anton)
            convertResourceIDToByteArray(R.drawable.apostolos_vamvouras)
            convertResourceIDToByteArray(R.drawable.arshad_ahmed)
            convertResourceIDToByteArray(R.drawable.artem_beliaikin_0)
            convertResourceIDToByteArray(R.drawable.artem_beliaikin_1)
            convertResourceIDToByteArray(R.drawable.artem_beliaikin_2)
            convertResourceIDToByteArray(R.drawable.artem_beliaikin_3)
            convertResourceIDToByteArray(R.drawable.artyom_korshunov)
            convertResourceIDToByteArray(R.drawable.aryo_lahap)
            convertResourceIDToByteArray(R.drawable.ashwini_chaudhary)
            convertResourceIDToByteArray(R.drawable.asit_khanda)
            convertResourceIDToByteArray(R.drawable.askar_ulzhabayev)
            convertResourceIDToByteArray(R.drawable.asmund_gimre)
            convertResourceIDToByteArray(R.drawable.austin_loveing)
            convertResourceIDToByteArray(R.drawable.austin_wade)
            convertResourceIDToByteArray(R.drawable.benjaima_kamel)
            convertResourceIDToByteArray(R.drawable.bimata_prathama)
            convertResourceIDToByteArray(R.drawable.brady_rogers)
            convertResourceIDToByteArray(R.drawable.brandon_jackson)
            convertResourceIDToByteArray(R.drawable.brendan_hollis)
            convertResourceIDToByteArray(R.drawable.brian_wangenheim)
            convertResourceIDToByteArray(R.drawable.bruno_gomiero)
            convertResourceIDToByteArray(R.drawable.cameron_sanborn)
            convertResourceIDToByteArray(R.drawable.carol_magalhaes)
            convertResourceIDToByteArray(R.drawable.catherine_zaidova)
            convertResourceIDToByteArray(R.drawable.charis_gegelman)
            convertResourceIDToByteArray(R.drawable.chelsey_faucher)
            convertResourceIDToByteArray(R.drawable.chermiti_mohamed_0)
            convertResourceIDToByteArray(R.drawable.chermiti_mohamed_1)
            convertResourceIDToByteArray(R.drawable.claudia_van)
            convertResourceIDToByteArray(R.drawable.cleyton_ewerton)
            convertResourceIDToByteArray(R.drawable.courtney_cook_0)
            convertResourceIDToByteArray(R.drawable.courtney_cook_1)
            convertResourceIDToByteArray(R.drawable.cristina_zaragoza)
            convertResourceIDToByteArray(R.drawable.dan_0)
            convertResourceIDToByteArray(R.drawable.dan_burton)
            convertResourceIDToByteArray(R.drawable.daniil_lobachev_0)
            convertResourceIDToByteArray(R.drawable.daniil_lobachev_1)
            convertResourceIDToByteArray(R.drawable.daoud_abismail)
            convertResourceIDToByteArray(R.drawable.darren_lawrence)
            convertResourceIDToByteArray(R.drawable.delfina_cocciardi)
            convertResourceIDToByteArray(R.drawable.denis_tuksar_0)
            convertResourceIDToByteArray(R.drawable.denis_tuksar_1)
            convertResourceIDToByteArray(R.drawable.diana_simumpande)
            convertResourceIDToByteArray(R.drawable.dias)
            convertResourceIDToByteArray(R.drawable.diego_sanchez)
            convertResourceIDToByteArray(R.drawable.dries_augustyns)
            convertResourceIDToByteArray(R.drawable.elevate)
            convertResourceIDToByteArray(R.drawable.emma_fabbri)
            convertResourceIDToByteArray(R.drawable.engin_akyurt)
            convertResourceIDToByteArray(R.drawable.erik_mclean)
            convertResourceIDToByteArray(R.drawable.eugene_lagunov)
            convertResourceIDToByteArray(R.drawable.filip_bunkens)
            convertResourceIDToByteArray(R.drawable.frankie_cordoba_0)
            convertResourceIDToByteArray(R.drawable.frankie_cordoba_1)
            convertResourceIDToByteArray(R.drawable.frankie_cordoba_2)
            convertResourceIDToByteArray(R.drawable.fred_kearney)
            convertResourceIDToByteArray(R.drawable.freemodels_agency)
            convertResourceIDToByteArray(R.drawable.gabi_miranda)
            convertResourceIDToByteArray(R.drawable.gary_edmonstone)
            convertResourceIDToByteArray(R.drawable.gean_montoya)
            convertResourceIDToByteArray(R.drawable.georg_arthur)
            convertResourceIDToByteArray(R.drawable.ghen_mar_cuano)
            convertResourceIDToByteArray(R.drawable.gian_cescon)
            convertResourceIDToByteArray(R.drawable.godwin_angeline)
            convertResourceIDToByteArray(R.drawable.gursimrat_ganda_0)
            convertResourceIDToByteArray(R.drawable.gursimrat_ganda_1)
            convertResourceIDToByteArray(R.drawable.gursimrat_ganda_2)
            convertResourceIDToByteArray(R.drawable.hadi_yazdi_aznaveh)
            convertResourceIDToByteArray(R.drawable.hamid_tajik)
            convertResourceIDToByteArray(R.drawable.harry_cunningham)
            convertResourceIDToByteArray(R.drawable.helena_lopes_0)
            convertResourceIDToByteArray(R.drawable.helena_lopes_1)
            convertResourceIDToByteArray(R.drawable.helena_lopes_2)
            convertResourceIDToByteArray(R.drawable.hennie_stander)
            convertResourceIDToByteArray(R.drawable.huseyin_topcu)
            convertResourceIDToByteArray(R.drawable.igor_figueredo)
            convertResourceIDToByteArray(R.drawable.ilona_panych)
            convertResourceIDToByteArray(R.drawable.irina_nakonechnaya)
            convertResourceIDToByteArray(R.drawable.isaiah_mcclean)
            convertResourceIDToByteArray(R.drawable.israfil_molla)
            convertResourceIDToByteArray(R.drawable.iyin_john_onaeko)
            convertResourceIDToByteArray(R.drawable.j_meier)
            convertResourceIDToByteArray(R.drawable.jabez_samuel)
            convertResourceIDToByteArray(R.drawable.jacob_johnson)
            convertResourceIDToByteArray(R.drawable.jamie_brown)
            convertResourceIDToByteArray(R.drawable.jamie_sutter)
            convertResourceIDToByteArray(R.drawable.jason_yoder)
            convertResourceIDToByteArray(R.drawable.jc_gellidon)
            convertResourceIDToByteArray(R.drawable.jd_chow)
            convertResourceIDToByteArray(R.drawable.jernej_graj)
            convertResourceIDToByteArray(R.drawable.jessica_wilson)
            convertResourceIDToByteArray(R.drawable.joel_mott_0)
            convertResourceIDToByteArray(R.drawable.joel_mott_1)
            convertResourceIDToByteArray(R.drawable.joel_naren)
            convertResourceIDToByteArray(R.drawable.joey_nicotra)
            convertResourceIDToByteArray(R.drawable.joeyy_lee)
            convertResourceIDToByteArray(R.drawable.johan_mouchet)
            convertResourceIDToByteArray(R.drawable.jonathan_borba)
            convertResourceIDToByteArray(R.drawable.jorge_cesar)
            convertResourceIDToByteArray(R.drawable.julian_gentilezza)
            convertResourceIDToByteArray(R.drawable.justin_chen)
            convertResourceIDToByteArray(R.drawable.jyotirmoy_gupta)
            convertResourceIDToByteArray(R.drawable.kal_visuals)
            convertResourceIDToByteArray(R.drawable.kalei_peek)
            convertResourceIDToByteArray(R.drawable.kartikey_panchal)
            convertResourceIDToByteArray(R.drawable.kate_hliznitsova_0)
            convertResourceIDToByteArray(R.drawable.kate_hliznitsova_1)
            convertResourceIDToByteArray(R.drawable.keith_jonson)
            convertResourceIDToByteArray(R.drawable.keitravis_squire)
            convertResourceIDToByteArray(R.drawable.kirschner_amao)
            convertResourceIDToByteArray(R.drawable.kuman_baseuh)
            convertResourceIDToByteArray(R.drawable.letizia_agosta)
            convertResourceIDToByteArray(R.drawable.logan_weaver_0)
            convertResourceIDToByteArray(R.drawable.logan_weaver_1)
            convertResourceIDToByteArray(R.drawable.logan_weaver_2)
            convertResourceIDToByteArray(R.drawable.logan_weaver_3)
            convertResourceIDToByteArray(R.drawable.logan_weaver_4)
            convertResourceIDToByteArray(R.drawable.long_truong)
            convertResourceIDToByteArray(R.drawable.luis_machado)
            convertResourceIDToByteArray(R.drawable.luiza_braun)
            convertResourceIDToByteArray(R.drawable.lukas_eggers_0)
            convertResourceIDToByteArray(R.drawable.lukas_eggers_1)
            convertResourceIDToByteArray(R.drawable.marcel_strauss)
            convertResourceIDToByteArray(R.drawable.marcos_paulo_0)
            convertResourceIDToByteArray(R.drawable.marcos_paulo_1)
            convertResourceIDToByteArray(R.drawable.mariano_nocetti)
            convertResourceIDToByteArray(R.drawable.marie_michele)
            convertResourceIDToByteArray(R.drawable.marina_kazmirova)
            convertResourceIDToByteArray(R.drawable.marius_muresan_0)
            convertResourceIDToByteArray(R.drawable.marius_muresan_1)
            convertResourceIDToByteArray(R.drawable.marivi_pazos)
            convertResourceIDToByteArray(R.drawable.markel_hall)
            convertResourceIDToByteArray(R.drawable.martin_pechy)
            convertResourceIDToByteArray(R.drawable.matheus_farias)
            convertResourceIDToByteArray(R.drawable.matteo_vistocco)
            convertResourceIDToByteArray(R.drawable.max_felner)
            convertResourceIDToByteArray(R.drawable.maxim_potkin_0)
            convertResourceIDToByteArray(R.drawable.maxim_potkin_1)
            convertResourceIDToByteArray(R.drawable.md_duran)
            convertResourceIDToByteArray(R.drawable.meghan_schiereck)
            convertResourceIDToByteArray(R.drawable.michael_uebler)
            convertResourceIDToByteArray(R.drawable.miguel_castellanos)
            convertResourceIDToByteArray(R.drawable.mikael_frivold_0)
            convertResourceIDToByteArray(R.drawable.mikael_frivold_1)
            convertResourceIDToByteArray(R.drawable.molnar_balint)
            convertResourceIDToByteArray(R.drawable.nasjere_williams)
            convertResourceIDToByteArray(R.drawable.nathan_mullet)
            convertResourceIDToByteArray(R.drawable.nikolay_vybornov)
            convertResourceIDToByteArray(R.drawable.offcut)
            convertResourceIDToByteArray(R.drawable.oleh_yakubiv)
            convertResourceIDToByteArray(R.drawable.philipp_lansing)
            convertResourceIDToByteArray(R.drawable.photographe_evjf)
            convertResourceIDToByteArray(R.drawable.pritomsreeta_borah)
            convertResourceIDToByteArray(R.drawable.qim_manifester)
            convertResourceIDToByteArray(R.drawable.rahabi_khan)
            convertResourceIDToByteArray(R.drawable.rene_ranisch)
            convertResourceIDToByteArray(R.drawable.robert_mcgowan)
            convertResourceIDToByteArray(R.drawable.robson_hatsukami)
            convertResourceIDToByteArray(R.drawable.rohan_pandavadra)
            convertResourceIDToByteArray(R.drawable.roman_purtov)
            convertResourceIDToByteArray(R.drawable.rostyslav_savchyn)
            convertResourceIDToByteArray(R.drawable.rumarin)
            convertResourceIDToByteArray(R.drawable.russ_mcelroy)
            convertResourceIDToByteArray(R.drawable.saul_bucio)
            convertResourceIDToByteArray(R.drawable.screen_post)
            convertResourceIDToByteArray(R.drawable.sebastian_pena)
            convertResourceIDToByteArray(R.drawable.sena)
            convertResourceIDToByteArray(R.drawable.shaiful_islam)
            convertResourceIDToByteArray(R.drawable.shotlist)
            convertResourceIDToByteArray(R.drawable.siviwe_kapteyn)
            convertResourceIDToByteArray(R.drawable.stefan_stefancik)
            convertResourceIDToByteArray(R.drawable.stem)
            convertResourceIDToByteArray(R.drawable.taneli_lahtinen)
            convertResourceIDToByteArray(R.drawable.tavershima_shande)
            convertResourceIDToByteArray(R.drawable.tim_mossholder)
            convertResourceIDToByteArray(R.drawable.tommaso_zandri)
            convertResourceIDToByteArray(R.drawable.tyler_sakil_0)
            convertResourceIDToByteArray(R.drawable.tyler_sakil_1)
            convertResourceIDToByteArray(R.drawable.valentin_lacoste)
            convertResourceIDToByteArray(R.drawable.valerie_elash)
            convertResourceIDToByteArray(R.drawable.vicky_hladynets)
            convertResourceIDToByteArray(R.drawable.victoria_priessnitz)
            convertResourceIDToByteArray(R.drawable.vince_fleming)
            convertResourceIDToByteArray(R.drawable.vinit_vispute)
            convertResourceIDToByteArray(R.drawable.vladimir_fedotov)
            convertResourceIDToByteArray(R.drawable.wajih_ghali)
            convertResourceIDToByteArray(R.drawable.watoker_derrick)
            convertResourceIDToByteArray(R.drawable.westwind_air)
            convertResourceIDToByteArray(R.drawable.yongzheng_xu)
            convertResourceIDToByteArray(R.drawable.zach_kadolph)
            convertResourceIDToByteArray(R.drawable.zana_latif)
        }
    }

    private suspend fun convertResourceIDToByteArray(resourceID: Int) {
        withContext(IO) {

            System.gc()
            sendByteArrayToServer(resourceID)
        }
    }

    private val userPictureCroppedWidth = 1200
    private val userPictureCroppedHeight = 1930
    private val userPictureThumbnailCroppedHeight = 200
    private val userPictureThumbnailCroppedWidth =
        (userPictureThumbnailCroppedHeight.toFloat() * userPictureCroppedWidth.toFloat() / userPictureCroppedHeight.toFloat()).toInt()

    private suspend fun sendByteArrayToServer(resourceID: Int) {
        withContext(IO) {
            Log.i("TestByteClient", "Starting ${resources.getResourceEntryName(resourceID)}")

            val picBAOS = ByteArrayOutputStream()
            val thumbnailBAOS = ByteArrayOutputStream()
            var bitmap = BitmapFactory.decodeResource(resources, resourceID)

            val thumbnailBitmap = ThumbnailUtils.extractThumbnail(
                bitmap, userPictureThumbnailCroppedWidth, userPictureThumbnailCroppedHeight
            )

            thumbnailBitmap?.compress(Bitmap.CompressFormat.JPEG, 30, thumbnailBAOS)
            val thumbnailByteArray = thumbnailBAOS.toByteArray()

            //set bitmap aspect ratio
            bitmap = ThumbnailUtils.extractThumbnail(
                bitmap,
                userPictureCroppedWidth,
                userPictureCroppedHeight,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT
            )

            bitmap?.compress(Bitmap.CompressFormat.JPEG, 30, picBAOS)
            val byteArray = picBAOS.toByteArray()

            val picMb = byteArray.size / (1024 * 1024)
            val picKb = (byteArray.size % (1024 * 1024)) / 1024
            val picBytes = byteArray.size % (1024)

            val thumbPicMb = thumbnailByteArray.size / (1024 * 1024)
            val thumbPicKb = (thumbnailByteArray.size % (1024 * 1024)) / 1024
            val thumbPicBytes = thumbnailByteArray.size % (1024)

            Log.i("TestByteClient", "Picture Size $picMb Mb $picKb Kb $picBytes Bytes")
            Log.i(
                "TestByteClient",
                "Thumbnail Size $thumbPicMb Mb $thumbPicKb Kb $thumbPicBytes Bytes"
            )

            if (byteArray.size < 1024 * 1024) {
                SendPicturesForTesting()
                    .runClient(byteArray, thumbnailByteArray, createdChannel)
            } else {
                Log.i(
                    "TestByteClient",
                    "Picture not saved; size: ${byteArray.size}; maxSize: ${1024 * 1024}"
                )
            }

            picBAOS.reset()
            thumbnailBAOS.reset()
            bitmap.recycle()

            Log.i("TestByteClient", "Finishing ${resources.getResourceEntryName(resourceID)}")
        }
    }

    class MakeshiftCoroutineConditionVariable {

        private val channel: Channel<Unit> = Channel(0)

        suspend fun wait() {
            channel.receive()
        }

        suspend fun wait(
            timeInMillis: Long,
            coRoutineContext: CoroutineDispatcher = IO,
        ) {
            val job = CoroutineScope(coRoutineContext).launch {
                delay(timeInMillis)
                channel.trySend(Unit)
            }

            channel.receive()
            job.cancel("no_longer_needed", null)
        }

        fun notifyOne() {
            channel.trySend(Unit)
        }
    }

    private suspend fun loadBalanceServer(request: RetrieveServerLoad.RetrieveServerLoadRequest, channel: ManagedChannel) {
//        val channelz = AndroidChannelBuilder.forTarget("multiaddress")
//            //.executor(Executors.newCachedThreadPool())
//            .context(requireContext().applicationContext)
//            .usePlaintext()
//            .build()

        val client = RetrieveServerLoadServiceGrpc.newStub(channel)

        val conditionVariable = MakeshiftCoroutineConditionVariable()

        var response =
            RetrieveServerLoad.RetrieveServerLoadResponse.getDefaultInstance()

        val start = System.currentTimeMillis()

        var firstDone = false
        var secondDone = false

        client
            .retrieveServerLoadRPC(request,
                object : StreamObserver<RetrieveServerLoad.RetrieveServerLoadResponse> {
                    override fun onNext(value: RetrieveServerLoad.RetrieveServerLoadResponse) {
                        Log.i(
                            "retrieveServerTime",
                            "ONE client: ${System.currentTimeMillis() - start}ms"
                        )
                        response = value
                    }

                    override fun onError(t: Throwable?) {
                        firstDone = true

                        if (secondDone)
                            conditionVariable.notifyOne()
                    }

                    override fun onCompleted() {
                        firstDone = true

                        if (secondDone)
                            conditionVariable.notifyOne()
                    }
                })

        delay(1000)

        val clientTwo = RetrieveServerLoadServiceGrpc.newStub(channel)

        val requestTwo = RetrieveServerLoad.RetrieveServerLoadRequest.newBuilder()
            .setRequestNumClients(true)
            .build()

        //clientTwo.withCallCredentials()
        clientTwo
            .retrieveServerLoadRPC(requestTwo,
                object : StreamObserver<RetrieveServerLoad.RetrieveServerLoadResponse> {
                    override fun onNext(value: RetrieveServerLoad.RetrieveServerLoadResponse) {
                        Log.i(
                            "retrieveServerTime",
                            "TWO client: ${System.currentTimeMillis() - start}ms"
                        )
                        response = value
                    }

                    override fun onError(t: Throwable?) {
                        secondDone = true

                        if (firstDone)
                            conditionVariable.notifyOne()
                    }

                    override fun onCompleted() {
                        secondDone = true

                        if (firstDone)
                            conditionVariable.notifyOne()
                    }
                })

        conditionVariable.wait()
    }
}
