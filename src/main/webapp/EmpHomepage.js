const reimTable = document.getElementById('reimbursements');
const logoutButton = document.getElementById('logout');
const detailsButton = document.getElementById('details');
const header = document.getElementById('header');
const body = document.getElementById('body');


const renderReimbursements = (reims) => {
   if (reims[0])
   {
      for (reim in reims)
      {
         // add a row to the table
         let row = reimTable.insertRow(-1);
         
         // amount
         let dataCell = document.createElement('td');
         dataCell.innerHTML = `\$${reims[reim].expenseAmount}.00`;
         row.appendChild(dataCell);
         
         // notes
         let dataCell2 = document.createElement('td');
         dataCell2.innerHTML = reims[reim].notes;
         row.appendChild(dataCell2);
         
         // status
         let dataCell3 = document.createElement('td');
         dataCell3.innerHTML = reims[reim].status;
         row.appendChild(dataCell3);
         setColor(dataCell3);
      }
   }
   else 
   {
      let notification = document.createElement('h2');
      notification.innerHTML = 'No reimbursements found for you';
      body.appendChild(notification);
   }
};

function setColor(cell) {
   cellText = cell.innerHTML;
   cellColor = cellText == 'pending' ? 'blue' : cellText == 'approved' ? 'green' : 'gray';
   cell.style.contentColor = cellColor;
   // if (cellColor == 'black') cell.style.color = 'white';
}

document.addEventListener('DOMContentLoaded', () => {
   // get reimbursements
   fetch('http://localhost:8080/project1/reimbursements')
   .then((resp) => resp.json())
   .then((reims) => renderReimbursements(reims))
});


logoutButton.addEventListener('click', () => {
   fetch('http://localhost:8080/project1/logout');
});