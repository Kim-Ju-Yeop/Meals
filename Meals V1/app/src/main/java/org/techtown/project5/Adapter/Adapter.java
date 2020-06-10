package org.techtown.project5.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.techtown.project5.Model.DTO;
import org.techtown.project5.R;

import java.util.ArrayList;

@SuppressWarnings({"ALL", "SyntaxError"})
public class Adapter extends BaseAdapter {

    // DTO 클래스를 참조해서 ArrayList 배열인 listCustom 배열을 생성하였습니다.
    private ArrayList<DTO> listCustom = new ArrayList<>();


    // CustomViewHoler 클래스의 맴버변수입니다.
    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
    }

    // listCustom 배열에 값을 추가합니다. DTO 클래스를 사용해서
    public void addItem(DTO dto) {
        listCustom.add(dto);
        notifyDataSetChanged();
    }

    // listCustom 배열을 초기화합니다.
    public void clear() {
        listCustom.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // listCustom 배열의 크기를 반환합니다.
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // listCustom 배열 안에 들어있는 아이템을 반환합니다.
    // ImageView 1개 textView 2개
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // item 을 실제로 보여주는 매서드입니다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;

        // 파라미터로 전달받은 convertView가 null 일 때 조건이 성립합니다.
        if (convertView == null) {

            // convertView 변수에 item_costom 레이아웃을 인플레이트 합니다.
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_costom, null, false);

            // CustomViewHolder() 매서드를 사용하여 holder 인스턴스를 생성하였습니다.
            holder = new CustomViewHolder();

            // holder 의 속성을 사용하여 각각의 텍스트 뷰들을 초기화하고있습니다.
            holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.text_content);

            // setTag 속성을 사용하여 holder 를 전달합니다.
            convertView.setTag(holder);
        }

        // convertView 가 null 이 아닐 때 조건에 성립합니다.
        else {

            holder = (CustomViewHolder) convertView.getTag();
        }

        // DTO 클래스를 참조해서 dto 변수를 생성하였습니다.
        // dto 변수안에 listCustom 배열의 값을 저장합니다.
        DTO dto = listCustom.get(position);

        // 입력받은 제목과 내용물을 저장합니다.
        holder.textTitle.setText(dto.getTitle());
        holder.textContent.setText(dto.getContent());

        return convertView;
    }


}