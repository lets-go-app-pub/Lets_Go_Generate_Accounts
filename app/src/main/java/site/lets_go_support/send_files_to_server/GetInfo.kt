package site.lets_go_support.send_files_to_server

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import site.lets_go_support.send_files_to_server.databinding.FragmentGetInfoBinding

class GetInfo : Fragment() {

    private var _binding: FragmentGetInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetInfoBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_getInfo_to_enterInfo)
        }

        binding.getInfoButton.setOnClickListener {

            /*if(client.logError() == null)
                Log.i("LogErrorClient", "NULL")
            else
                Log.i("LogErrorClient", "Return Msg: ${client.logError()}")
*/
            /*
            val values = SendNameClient.runClient(myViewGrop.enterFirstNameEditText.text.toString())

            myViewGrop.firstNameTextView.text = values[0]
            myViewGrop.lastNameTextView.text = values[1]
            myViewGrop.phoneTextView.text = values[2]

             */

        }

        return binding.root
    }

}
