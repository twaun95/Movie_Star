package com.twaun95.moviestar.presentation.dialog

import androidx.fragment.app.FragmentManager
import com.twaun95.moviestar.R
import com.twaun95.moviestar.databinding.FragmentDialogBinding
import com.twaun95.moviestar.presentation.base.BaseDialog
import com.twaun95.moviestar.presentation.extensions.setOnSingleClickListener

class DialogBookMark(
    private val type: TYPE,
    private var onCancelListener : (()->Unit)? = null,
    private var onConfirmListener : (()->Unit)? = null
) : BaseDialog<FragmentDialogBinding>(R.layout.fragment_dialog){

    enum class TYPE{
        ADD,
        DELETE
    }

    override fun initView() {
        super.initView()

        type.run {
            if (type == TYPE.ADD) {
                binding.tvTitle.text = ""
                binding.tvMessage.text = "즐겨찾에 추가하시겠습니까?"
                binding.btnComplete.text = "즐겨찾기"
            } else {
                binding.tvTitle.text = ""
                binding.tvMessage.text = "즐겨찾기에서 제거하시겠습니까?"
                binding.btnComplete.text = "즐겨찾기 제거"
            }
        }
    }

    override fun setEvent() {
        super.setEvent()

        binding.btnCancel.setOnSingleClickListener {
            onCancelListener?.invoke()
            dismiss()
        }
        binding.btnComplete.setOnSingleClickListener {
            onConfirmListener?.invoke()
            dismiss()
        }
    }

    companion object {
        private const val TAG = "BookMarkDialog"

        fun show(
            fragmentManager: FragmentManager,
            type: TYPE,
            onCancelListener : (()->Unit)? = null,
            onConfirmListener : (()->Unit)? = null
        ) {
            return DialogBookMark(type, onCancelListener, onConfirmListener)
                .show(fragmentManager, TAG)
        }
    }
}