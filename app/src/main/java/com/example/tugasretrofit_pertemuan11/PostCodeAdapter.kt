// PostCodeAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasretrofit_pertemuan11.database.PostCode
import com.example.tugasretrofit_pertemuan11.database.PostCodeDao
import com.example.tugasretrofit_pertemuan11.database.PostCodeRoomDatabase

import com.example.tugasretrofit_pertemuan11.databinding.ItemPostcodeBinding
import com.example.tugasretrofit_pertemuan11.model.PostCodes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class PostCodeAdapter(private val postCodes: List<PostCodes>) :
    RecyclerView.Adapter<PostCodeAdapter.PostCodeViewHolder>() {
    private lateinit var mPostCodeDao: PostCodeDao
    private lateinit var executorService: ExecutorService
    private var updateId: Int = 0

    inner class PostCodeViewHolder(private val binding: ItemPostcodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postCode: PostCodes) {
            binding.textViewKecamatan.text = "Kecamatan : ${postCode.kecamatan}"
            binding.textViewKelurahan.text = "Kelurahan : ${postCode.kelurahan}"
            binding.textViewKodepos.text = "Kode Pos : ${postCode.kodepos}\n-------------------------------------------------------------------------------------------------"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCodeViewHolder {
        val binding = ItemPostcodeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        executorService = Executors.newSingleThreadExecutor()
        val db = PostCodeRoomDatabase.getDatabase(binding.root.context)
        mPostCodeDao = db!!.postCodeDao()!!

        with(binding) {
            btnFavorite.setOnClickListener {
                insert(
                    PostCode(
                        kecamatan = textViewKecamatan.text.toString(),
                        kelurahan = textViewKelurahan.text.toString(),
                        kodepos = textViewKodepos.text.toString()
                    )
                )
            }
        }

//        with(binding) {
//            btnFavorite.setOnClickListener {
//                val kecamatan = textViewKecamatan.text.toString()
//                val kelurahan = textViewKelurahan.text.toString()
//                val kodepos = textViewKodepos.text.toString()
//
//                // Cek jika kelurahan sudah ada di database
//                val existingPostCode = mPostCodeDao.getPostCodeByKelurahan(kelurahan)
//
//                if (existingPostCode == null) {
//                    // Insert data jika belum ada
//                    insert(
//                        PostCode(
//                            kecamatan = kecamatan,
//                            kelurahan = kelurahan,
//                            kodepos = kodepos
//                        )
//                    )
//                    Toast.makeText(binding.root.context, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
//                } else {
//                    // Berikan notifikasi bahwa data sudah ada
//                    Toast.makeText(binding.root.context, "Kelurahan sudah ada di database!", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }



        return PostCodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostCodeViewHolder, position: Int) {
        holder.bind(postCodes[position])
    }

    override fun getItemCount(): Int = postCodes.size

    fun insert(postCode: PostCode) {
        executorService.execute {
            mPostCodeDao.insert(postCode)
        }
    }
}
