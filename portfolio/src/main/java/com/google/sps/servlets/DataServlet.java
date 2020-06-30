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
        public void init(){
            // create new message array if null
            if(messages == null){
                messages = new ArrayList<String>();
            }
        }
        
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
            // receive the comments from response.getParamaters 
            String userName = parameterInputValid(request, "username") ? request.getParameter("username") : null;
            String specificComment = parameterInputValid(request, "comment") ? request.getParameter("comment") : null;

            // TODO: make an object for the comments to take all the info (name, time, video/picture)
            String completeComment = userName + " says " + specificComment; 
            
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

        /** method that returns true if the user passed in input and 
        false if no input passed in for specific parameter */
        private boolean parameterInputValid(HttpServletRequest request, String parameterName) {
            return (null != request.getParameter(parameterName));  
        }
}
