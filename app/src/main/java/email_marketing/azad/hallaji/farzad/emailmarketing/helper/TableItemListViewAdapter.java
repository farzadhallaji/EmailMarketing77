package email_marketing.azad.hallaji.farzad.emailmarketing.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import email_marketing.azad.hallaji.farzad.emailmarketing.Make_Email;
import email_marketing.azad.hallaji.farzad.emailmarketing.R;
import email_marketing.azad.hallaji.farzad.emailmarketing.Report;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ListPersonItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by farzad on 10/9/2016.
 */
public class TableItemListViewAdapter extends ArrayAdapter<ListPersonItem> {

    private Context mContext;
    private List<ListPersonItem> personlist = new ArrayList<>();



    public TableItemListViewAdapter(Context context, List<ListPersonItem> objects) {

        super(context,0, objects);
        this.mContext=context;
        this.personlist=objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if(position== Make_Email.selectedList_ListItem && position!=0){
            view = vi.inflate(R.layout.selecteditem, null);
        }else{
            view = vi.inflate(R.layout.tablelistview, null);
        }

        ListPersonItem mokhatabin = personlist.get(position);

        TextView txt_mail = (TextView) view.findViewById(R.id.ContentTableView);
        txt_mail.setText(mokhatabin.getName());

        TextView txt_name = (TextView) view.findViewById(R.id.IdTableListView);
        txt_name.setText(mokhatabin.getID());




        return view;
    }

}
