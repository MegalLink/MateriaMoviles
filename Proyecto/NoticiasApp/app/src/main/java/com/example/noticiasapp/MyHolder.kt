package com.example.noticiasapp

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


//Aqui en el holder puedo ejecutar acciones en el init si es que capturo algún elemento
class MyHolder (view:View):androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val idTextView:TextView
    val tituloTextView: TextView
    val imgNoticia:ImageView
    val descripcionTextView:TextView
    val sourceTextView:TextView
    val buttonDialog:Button
    init{

        idTextView=view.findViewById(R.id.tv_id_noticia)
        tituloTextView=view.findViewById(R.id.tv_titulo)
        descripcionTextView=view.findViewById(R.id.tv_descripcion)
        sourceTextView=view.findViewById(R.id.tv_source_name)
        imgNoticia=view.findViewById(R.id.iv_imagen_noticia)
        buttonDialog=view.findViewById(R.id.btn_sheet_dialog)
    }
}