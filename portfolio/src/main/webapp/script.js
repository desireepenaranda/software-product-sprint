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

    /**
    * Adds a random greeting to the page.
    */
    function addRandomGreeting() {
    const greetings =
        ['I am focusing on being a muscian', 'I am tapping into my artistic side: drawing & painting & writing', 'During the Q, I have built a deeper connection with my family members as well as my body'];

    // Pick a random greeting.
    const greeting = greetings[Math.floor(Math.random() * greetings.length)];

    // Add it to the page.
    const greetingContainer = document.getElementById('greeting-container');
    greetingContainer.innerText = greeting;
    }
   
     // async method that fetches the name from the /data tab
    async function getNameInfoUsingAsycAwait(){
        const response = await fetch('/data');
        const nameResponse = await response.text();
        document.getElementById('name-container').innerText = nameResponse;

    }
    
    document.addEventListener('DOMContentLoaded', function() {
            // method call to get the name response 
            getNameInfoUsingAsycAwait();
        });

