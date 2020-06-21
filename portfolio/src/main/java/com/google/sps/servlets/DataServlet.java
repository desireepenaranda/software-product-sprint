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
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    
    // instance vairables 
    ArrayList<String> messages;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");

    // only show the messages and print json if messages not null
    if(messages != null){
      // converting the message array list to a json 
        String jsonString = convertToJsonUsingGson(messages);

        // send the json as the response  
        response.setContentType("application/json;");
        response.getWriter().println(jsonString);
    }

  }

   @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // TODO: data store & entity creation 
    Entity taskEntity = new Entity("comment");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  
    // recieve the comments from response.getParamaters 
    String userName = getParamatersDataMassaged(request, "username");
    String specificComment = getParamatersDataMassaged(request, "comment");
    long timestamp = System.currentTimeMillis();

    // update entity properties to be of the 
    taskEntity.setProperty("username", userName);
    taskEntity.setProperty("text", specificComment);
    taskEntity.setProperty("timestamp", timestamp);
    datastore.put(taskEntity);

    // TODO: make an object for the comments to take all the info (name, time, video/picture)
    String completeComment = userName + " says " + specificComment; 
    
    // create new message array if null
    if(messages == null){
       messages = new ArrayList<String>();
    }
    //add the comments to the messages array 
    messages.add(completeComment);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }

  // method that takes in an arraylist of string and returns in json string form 
  private String convertToJsonUsingGson(ArrayList<String> messagesList) {
    Gson gson = new Gson();
    String json = gson.toJson(messagesList);
    return json;
  }

   /**  returns the massaged version of getting the data from the request*/
  private String getParamatersDataMassaged(HttpServletRequest request, String parameterName) {
    return request.getParameter(parameterName);
  }
}
