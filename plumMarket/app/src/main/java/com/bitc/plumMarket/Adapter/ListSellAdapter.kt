  package com.bitc.plumMarket.Adapter



  import android.util.Log
  import android.view.LayoutInflater
  import android.view.View
  import android.view.ViewGroup
  import androidx.appcompat.app.AppCompatActivity
  import androidx.recyclerview.widget.RecyclerView
  import com.bitc.plumMarket.Activity.PanmaeActivity
  import com.bitc.plumMarket.Data.ListData
  import com.bitc.plumMarket.Fragment.BottomSheetFragment
  import com.bitc.plumMarket.Fragment.OnSellCompleteListener
  import com.bitc.plumMarket.Fragment.SellHideFragment
  import com.bitc.plumMarket.Fragment.SellOngoingFragment
  import com.bitc.plumMarket.R
  import com.bitc.plumMarket.RetrofitBuilder
  import com.bitc.plumMarket.ViewHolder.ListSellViewHolder
  import com.bitc.plumMarket.databinding.ListSellItemBinding
  import retrofit2.Call
  import retrofit2.Callback
  import retrofit2.Response


  class ListSellAdapter(
    private var items: MutableList<ListData>,
    private val activity: AppCompatActivity,
    private val recyclerView: RecyclerView,
    private val sellCompleteListener: OnSellCompleteListener,
    private val tabPosition: String
  ) : RecyclerView.Adapter<ListSellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSellViewHolder {
      val binding = ListSellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return ListSellViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: ListSellViewHolder, position: Int) {

      val bind = holder.binding
      val idx = items[position].list_idx.toString()
      bind.tvListSellIdx.text = items[position].list_idx.toString()
      bind.tvListSellTitle.text = items[position].list_title
      bind.tvListSellMoney.text = "${items[position].list_money}원"
      bind.tvSellReservation.visibility = View.GONE

      RetrofitBuilder.api.selectSellState(idx).enqueue(object : Callback<ListData> {
        override fun onResponse(call: Call<ListData>, response: Response<ListData>) {
          if (response.isSuccessful) {
            val sellState = response.body()?.list_sell_state.toString()
            if(sellState == "3"){
              bind.tvSellReservation.visibility = View.VISIBLE
            }
          }
        }

        override fun onFailure(call: Call<ListData>, t: Throwable) {
          Log.d("error", t.localizedMessage)
        }
      })

      bind.btnEtc.setOnClickListener {
        val bottomSheetFragment = BottomSheetFragment(idx, bind.tvSellReservation,sellCompleteListener)
        bottomSheetFragment.show(activity.supportFragmentManager, bottomSheetFragment.tag)
      }


      when(tabPosition){
        "0" ->{
          bind.btnUpdate.setOnClickListener(){
            RetrofitBuilder.api.updateSellupdate(idx).enqueue(object : Callback<Void> {
              override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                  val viewPagerFragment = activity.supportFragmentManager.fragments.firstOrNull { it is SellOngoingFragment && it.isVisible } as? SellOngoingFragment
                  viewPagerFragment?.onSellComplete()
                }
              }

              override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("error", t.localizedMessage)
              }
            })
          }
        }

        "1"->{
          bind.btnUpdate.text = "후기 보내기"
        }

        "2"->{

          bind.btnUpdate.text = "숨기기 해제"

          bind.btnUpdate.setOnClickListener(){
            RetrofitBuilder.api.updateSellHideRemove(idx).enqueue(object : Callback<Void> {
              override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                  val viewPagerFragment = activity.supportFragmentManager.fragments.firstOrNull { it is SellHideFragment && it.isVisible } as? SellHideFragment
                  viewPagerFragment?.onSellComplete()
                }
              }

              override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("error", t.localizedMessage)
              }
            })
          }
        }
      }

    }

    fun setItems(newItems: List<ListData>) {
      items = newItems.toMutableList()
      notifyDataSetChanged()
      recyclerView.requestLayout()
    }
  }
