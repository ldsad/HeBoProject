package com.hebo.heboproject.adapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.hebo.heboproject.R;
import com.hebo.heboproject.views.PinyinComparator;

public class ExpressAdapter extends BaseAdapter implements SectionIndexer {

	private Context mContext;
	private String[] mNicks;
	
	private static String[] nicks = { "联邦快递（FEDEX）", "敦豪（DHL）", "天地快运（TNT）", "联合包裹（UPS）", "高保物流（GLEX）", "中国邮政（EMS）",
			"顺丰速运", "宅急送", "申通快递", "韵达快递", "圆通快递", "汇通快递", "中通快递", "快捷速递", "中铁快运" };

	
	public static Map<String,String> expressMap = new HashMap<String,String>();
	
	public static void init(){
		expressMap.put("顺丰速运", "sf");
		expressMap.put("佳吉物流", "jj");
		expressMap.put("全峰快递", "qfkd");
		expressMap.put("天天快递", "tt");
		expressMap.put("联合包裹（UPS）", "ups");
		expressMap.put("圆通速递", "yt");
		expressMap.put("韵达快运", "yunda");
		expressMap.put("德邦物流", "debangwuliu");
		expressMap.put("汇通快运", "huitongkuaidi");
		expressMap.put("宅急送", "zhaijisong");
		expressMap.put("中通速递", "zhongtong");
		Set<String> set = expressMap.keySet();
		nicks = new String[set.size()];
		int i = 0;
		for (String string : set) {
			nicks[i] = string;
			i++;
		}
		for (int j = 0; j < nicks.length; j++) {
			Log.i("===========", "========" + nicks[j]);
		}
		
		
	}
	
	public ExpressAdapter(Context mContext) {
		super();
		init();
		this.mContext = mContext;
		this.mNicks = nicks;
		Arrays.sort(mNicks, new PinyinComparator());
	}

	@Override
	public int getCount() {
		return mNicks.length;
	}

	@Override
	public Object getItem(int position) {
		return mNicks[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final String nickName = mNicks[position];
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.activity_express_item, null);
			viewHolder = new ViewHolder();
			viewHolder.tvCatalog = (TextView) convertView
					.findViewById(R.id.txt_expressitem_catalog);
			viewHolder.ivAvatar = (ImageView) convertView
					.findViewById(R.id.iv_expressitem);
			viewHolder.tvNick = (TextView) convertView
					.findViewById(R.id.txt_expressitem_nick);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String catalog = converterToFirstSpell(nickName.substring(0, 1));
		if (position == 0) {
			viewHolder.tvCatalog.setVisibility(View.VISIBLE);
			viewHolder.tvCatalog.setText(catalog);
		}else{
			String lastCatalog = converterToFirstSpell(mNicks[position-1]).substring(0,1);
			if(catalog.equals(lastCatalog)){
				viewHolder.tvCatalog.setVisibility(View.GONE);
			}else{
				viewHolder.tvCatalog.setVisibility(View.VISIBLE);
				viewHolder.tvCatalog.setText(catalog);
			}
		}
		viewHolder.ivAvatar.setImageResource(R.drawable.default_avatar);
		viewHolder.tvNick.setText(nickName);
		return convertView;
	}

	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < mNicks.length; i++) {  
            String l = converterToFirstSpell(mNicks[i]).substring(0, 1);  
            char firstChar = l.toUpperCase().charAt(0);  
            if (firstChar == section) {  
                return i;  
            }  
        } 
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	static class ViewHolder {
		TextView tvCatalog;// 目录
		ImageView ivAvatar;// 头像
		TextView tvNick;// 昵称
	}

	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				pinyinName += nameChar[i];
			}
		}

		return pinyinName;

	}

}
