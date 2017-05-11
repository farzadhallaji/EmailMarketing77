package email_marketing.azad.hallaji.farzad.emailmarketing.helper;

/**
 * Created by farzad on 11/16/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import email_marketing.azad.hallaji.farzad.emailmarketing.R;
import email_marketing.azad.hallaji.farzad.emailmarketing.Report;
import email_marketing.azad.hallaji.farzad.emailmarketing.model.ListPersonItem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by farzad on 10/9/2016.
 */
public class Table2Adapter extends ArrayAdapter<ListPersonItem> {

    private Context mContext;
    private List<ListPersonItem> personlist = new ArrayList<>();



    public Table2Adapter(Context context, List<ListPersonItem> objects) {

        super(context,0, objects);
        this.mContext=context;
        //(new ListPersonItem("ID", "List menu"));
        this.personlist=objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = vi.inflate(R.layout.tablelistview, null);
        ListPersonItem mokhatabin = personlist.get(position);

        TextView txt_mail = (TextView) view.findViewById(R.id.ContentTableView);
        txt_mail.setText(mokhatabin.getName());

        TextView txt_name = (TextView) view.findViewById(R.id.IdTableListView);
        txt_name.setText(mokhatabin.getID());



        return view;
    }

}
