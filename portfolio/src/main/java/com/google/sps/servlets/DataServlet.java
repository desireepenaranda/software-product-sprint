// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    
    // instance vairables 
    ArrayList<String> messages;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");

    //make sure that if the page is reloaded the messages are reset
    messages = new ArrayList<String>();

    String message1 = "nicolette is my sister who i love a lot";
    String message2 = "lilo is my brother";
    String message3 = "fran is me!";
    messages.add(message1);
    messages.add(message2);
    messages.add(message3);

    // converting the message array list to a json 
    String jsonString = convertToJsonUsingGson(messages);

    // send the json as the response  
    response.setContentType("application/json;");
    response.getWriter().println(jsonString);
  }
  // method that takes in an arraylist of string and returns in json string form 
  private String convertToJsonUsingGson(ArrayList<String> messagesList) {
    Gson gson = new Gson();
    String json = gson.toJson(messagesList);
    return json;
  }
}
