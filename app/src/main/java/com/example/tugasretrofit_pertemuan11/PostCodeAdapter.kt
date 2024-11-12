// PostCodeAdapter.kt
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.tugasretrofit_pertemuan11.databinding.ItemPostcodeBinding
import com.example.tugasretrofit_pertemuan11.model.PostCodes



class PostCodeAdapter(private val postCodes: List<PostCodes>) :
    RecyclerView.Adapter<PostCodeAdapter.PostCodeViewHolder>() {

    inner class PostCodeViewHolder(private val binding: ItemPostcodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postCode: PostCodes) {
            binding.textViewKecamatan.text = "Kecamatan : ${postCode.kecamatan}"
            binding.textViewKelurahan.text = "Kelurahan : ${postCode.kelurahan}"
            binding.textViewKodepos.text = "Kode Pos : ${postCode.kodepos}\n--------------------------------"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCodeViewHolder {
        val binding = ItemPostcodeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostCodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostCodeViewHolder, position: Int) {
        holder.bind(postCodes[position])
    }

    override fun getItemCount(): Int = postCodes.size
}
