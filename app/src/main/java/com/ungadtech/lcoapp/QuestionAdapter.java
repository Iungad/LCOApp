package com.ungadtech.lcoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Question[] questionArray;
    private Context context;

    public QuestionAdapter(Context context,Question[] questions) {
        this.context = context;
        this.questionArray = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_recycler_layout,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.questionText.setText(questionArray[position].getQuestion());
        holder.questionNumber.setText("Q."+(position+1));
    }

    @Override
    public int getItemCount() {
        return questionArray.length;
    }


    class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView questionNumber;
        private TextView questionText;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.question_number_text);
            questionText = itemView.findViewById(R.id.question_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Que. "+questionArray[getAdapterPosition()].getQuestion()+"\n\n"
            +"Ans. "+questionArray[getAdapterPosition()].getAnswer());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
