package com.mbd.arestpigetrequest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    Context context ;
    List<User>  userList;
    Dialog mydialog;


    public UserAdapter(Context context,List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        ViewHolder vHolder = new ViewHolder(view);




        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (userList != null && userList.size() > 0) {
            User user = userList.get(position);
            holder.idUser.setText(String.valueOf(user.getId()));

            URL url = null;
            try {
                url = new URL(user.getAvatar());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.idAvatar.setImageBitmap(bmp);
            holder.idName.setText(user.getFirst_name());
            holder.idEmail.setText(user.getEmail());

        }else{
            return;
        }



        holder.user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("clicktest", String.valueOf(userList.get(position).getFirst_name()));
                Toast.makeText(context,"Test",Toast.LENGTH_SHORT);
                //dialog ini
                mydialog = new Dialog(context);
                mydialog.setContentView(R.layout.dialog_layout);
                TextView dialogName = (TextView) mydialog.findViewById(R.id.name);
                TextView dialogEmail = (TextView) mydialog.findViewById(R.id.email);
                ImageView dialogAvatar = (ImageView) mydialog.findViewById(R.id.avatar);
                try {
                    dialogName.setText(userList.get(position).getFirst_name());
                    dialogEmail.setText(userList.get(position).getEmail());
                    dialogAvatar.setImageBitmap(BitmapFactory.decodeStream(new URL(userList.get(position).getAvatar()).openConnection().getInputStream()));
                }catch (Exception e){
                    Log.d("clicktest", String.valueOf(e));

                }
                mydialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TableRow user;
        TextView idUser,idName,idEmail;
        ImageView idAvatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            idUser = itemView.findViewById(R.id.idUser);
            idAvatar = itemView.findViewById(R.id.idAvatar);
            idName = itemView.findViewById(R.id.idName);
            idEmail = itemView.findViewById(R.id.idEmail);
        }
    }

}
