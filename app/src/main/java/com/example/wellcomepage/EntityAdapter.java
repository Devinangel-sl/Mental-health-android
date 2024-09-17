package com.example.wellcomepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.EntityViewHolder> {
    ArrayList<Entity> entities;

    public EntityAdapter(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    @NonNull
    @Override
    public EntityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_entity_adapter, parent, false);
        return new EntityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityViewHolder holder, int position) {
        Entity entity = entities.get(position);
        holder.property1.setText(entity.getProperty1());
        holder.property2.setText(entity.getProperty2());
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public static class EntityViewHolder extends RecyclerView.ViewHolder {
        TextView property1, property2;

        public EntityViewHolder(@NonNull View itemView) {
            super(itemView);
            property1 = itemView.findViewById(R.id.property1);
            property2 = itemView.findViewById(R.id.property2);
        }
    }
}

//public class EntityAdapter extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_entity_adapter);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}