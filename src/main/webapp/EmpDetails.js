const table = document.getElementById('details');
const logoutButton = document.getElementById('logout');


// details should be a single json object
const renderDetails = (details) => {

   let row = table.insertRow(-1);

   let dataCell = document.createElement('td');
   dataCell.innerHTML = details.firstname;
   row.appendChild(dataCell);
   
   dataCell = document.createElement('td');
   dataCell.innerHTML = details.lastname;
   row.appendChild(dataCell);
   
   dataCell = document.createElement('td');
   dataCell.innerHTML = details.city;
   row.appendChild(dataCell);
   
   dataCell = document.createElement('td');
   dataCell.innerHTML = details.state;
   row.appendChild(dataCell);

   dataCell = document.createElement('td');
   dataCell.innerHTML = details.username;
   row.appendChild(dataCell);

   dataCell = document.createElement('td');
   dataCell.innerHTML = details.password;
   row.appendChild(dataCell);

};


addEventListener('DOMContentLoaded', () => {
   fetch('http://localhost:8080/project1/details')
   .then((details) => details.json())
   .then((details) => renderDetails(details));
});

// logoutButton.addEventListener('click', () => {
//    fetch('http://localhost:8080/project1/logout');
// });