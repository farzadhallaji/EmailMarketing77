package email_marketing.azad.hallaji.farzad.emailmarketing.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import email_marketing.azad.hallaji.farzad.emailmarketing.Make_Email;
import email_marketing.azad.hallaji.farzad.emailmarketing.R;
import email_marketing.azad.hallaji.farzad.emailmarketing.Report;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ListPersonItem;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ReportItem;

/**
 * Created by farzad on 12/7/2016.
 */
public class report_listshow  extends ArrayAdapter<ReportItem>{

        private Context mContext;
        private List<ReportItem> reportItemList = new ArrayList<>();



        public report_listshow(Context context, List<ReportItem> objects) {

            super(context,0, objects);
            this.mContext=context;
            this.reportItemList=objects;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            View view;
            if(position== Report.selected_item_report){
                view = vi.inflate(R.layout.report_item2, null);
            }else{
                view = vi.inflate(R.layout.report_item, null);
            }
            ReportItem Ittem = reportItemList.get(position);

            TextView txt_total = (TextView) view.findViewById(R.id.total);
            txt_total.setText("Total: "+ Ittem.getT());

            TextView txt_Success = (TextView) view.findViewById(R.id.success);
            txt_Success.setText("Success: "+Ittem.getS());

            TextView txt_Read = (TextView) view.findViewById(R.id.read);
            txt_Read.setText("Read: "+ Ittem.getR());

            TextView txt_Clicked = (TextView) view.findViewById(R.id.clicked);
            txt_Clicked.setText("Clicked: "+Ittem.getC());

            TextView sentlist = (TextView) view.findViewById(R.id.sentlist);
            sentlist.setText("Sent List:"+Ittem.getList());


            String date="";
            Long aLong ;
            String dd=Ittem.getTime().trim();
            if (!dd.equals("")){
                aLong=Long.parseLong(dd);
                date=new java.text.SimpleDateFormat("MM/dd/yy")
                        .format(new java.util.Date (aLong*1000));
            }
            TextView senttime = (TextView) view.findViewById(R.id.senttime);
            senttime.setText("Sent Time:"+date);


            TextView senttemp = (TextView) view.findViewById(R.id.senttemp);
            senttemp.setText("Sent Temp:"+Ittem.getTemplate());

            return view;
        }

    }

