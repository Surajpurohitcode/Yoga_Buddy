package com.example.yogabuddy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yogabuddy.adpter.ChatBotAdpter;
import com.example.yogabuddy.databinding.FragmentChatBotBinding;
import com.example.yogabuddy.model.ChatModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatBotFragment extends Fragment {

    ArrayList<ChatModel> arrayList = new ArrayList<>();

    ChatBotAdpter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentChatBotBinding binding = FragmentChatBotBinding.inflate(inflater,container,false);

        binding.chatList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adpter = new ChatBotAdpter(arrayList,getActivity());
        binding.chatList.setAdapter(adpter);

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.inputBox.getText().toString();

                if (message.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please enter your message!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    arrayList.add(new ChatModel(message,1));
                    getResponse(message);
                    binding.inputBox.setText("");
                }

            }
        });



        return binding.getRoot();
    }

    private void getResponse(String message) {

        String url = "http://api.brainshop.ai/get?bid=178795&key=7tEBUIzXJvaQDTY5&uid=[uid]&msg="+message;

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // in on response method we are extracting data
                    // from json response and adding this response to our array list.
                    String botResponse = response.getString("cnt");
                    arrayList.add(new ChatModel(botResponse,2));

                    // notifying our adapter as data changed.
                    adpter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                    // handling error response from bot.
                    arrayList.add(new ChatModel("No response", 2));
                    adpter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error handling.
                arrayList.add(new ChatModel("No response", 2));
                Toast.makeText(getActivity(), "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });

        // at last adding json object
        // request to our queue.
        queue.add(jsonObjectRequest);

    }
}