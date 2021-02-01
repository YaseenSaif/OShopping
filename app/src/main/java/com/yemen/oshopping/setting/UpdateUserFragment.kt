package com.yemen.oshopping.setting


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yemen.oshopping.R
import com.yemen.oshopping.model.User
import com.yemen.oshopping.sharedPreferences.UserSharedPreferences
import com.yemen.oshopping.ui.ShowUsersReportsActivity
import com.yemen.oshopping.vendor.UpdateProductFragmentArgs
import com.yemen.oshopping.viewmodel.OshoppingViewModel
private const val PARAM1="USER"
class UpdateUserFragment : Fragment() {

  //val uargs: UpdateUserFragmentArgs by navArgs()
    private var uri: String? = null
    var imageName:String?=null
    lateinit var fNameEditText: EditText
    lateinit var lNameEditText: EditText
    lateinit var addressEditText: EditText
    lateinit var phoneNumberEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var editImageBTN: ImageButton
    lateinit var updateUserBtn:Button
    lateinit var userImage: ImageView
     var user: User? = null
    private lateinit var oshoppingViewModel: OshoppingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user=arguments?.getSerializable(PARAM1) as User?
        oshoppingViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update_user, container, false)
        fNameEditText = view.findViewById(R.id.first_name_edit_text)
        lNameEditText = view.findViewById(R.id.last_name_edit_text)
        addressEditText = view.findViewById(R.id.address_edit_text)
        phoneNumberEditText = view.findViewById(R.id.phone_edit_text)
        detailsEditText = view.findViewById(R.id.details_edit_text)
        updateUserBtn=view.findViewById(R.id.updateUserBtn)

        userImage = view.findViewById(R.id.user_image)
        fNameEditText.setText(user?.first_name.toString())
        lNameEditText.setText(user?.last_name.toString())
        addressEditText.setText(user?.address)
        phoneNumberEditText.setText(user?.phone_number)
        detailsEditText.setText(user?.details)
        Picasso.get().load(uri+user?.email).into(userImage)


        updateUserBtn.setOnClickListener {
            val user = User(
                user_id = oshoppingViewModel.getStoredUserId(),
                first_name = fNameEditText.text.toString(),
                last_name = lNameEditText.text.toString(),
                phone_number = phoneNumberEditText.text.toString(),
                address = addressEditText.text.toString(),
                details = detailsEditText.text.toString()
//                ,image = imageName
            )
            oshoppingViewModel.updateUser(user)
            restartActivity()
        }
        return view
    }
    private fun restartActivity() {
        val intent = Intent(this@UpdateUserFragment.context, ShowUserActivity::class.java)
        startActivity(intent)
        if (activity != null) {
            requireActivity().finish()
        }
    }
    companion object{
        fun newInstance(param1 :User):UpdateUserFragment{
            return UpdateUserFragment().apply {
                arguments=Bundle().apply {
                    putSerializable(PARAM1,param1)
                }
            }
        }
    }

}