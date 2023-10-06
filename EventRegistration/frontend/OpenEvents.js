document.addEventListener("DOMContentLoaded", function () {
  const eventTable = document.getElementById("eventTable");
  fetch('http://localhost:8080/event/openEvent')
  .then(response => response.json())
  .then(events => {
    const eventDataString = encodeURIComponent(JSON.stringify(events.listData));
    console.log(eventDataString)
    const eventData = JSON.parse(decodeURIComponent(eventDataString));
    console.log(eventData)
    populateEventTable(eventData);
  })
 // const urlParams = new URLSearchParams(window.location.search);
  //const eventDataString = urlParams.get("eventData");
  //const eventData = JSON.parse(decodeURIComponent(eventDataString));
  

  //populateEventTable(eventData);

  function populateEventTable(eventList) {
    eventTable.innerHTML = "";
    const headers = [
      "Event Id",
      "Event Name",
      "Event Description",
      "Event Duration",
      "Event Location",
      "Event Fees",
      "Event Max Participants",
      "Event Tags",
      "Action",
    ];
    const headerRow = eventTable.insertRow();
    headers.forEach((headerText) => {
      const th = document.createElement("th");
      th.textContent = headerText;
      headerRow.appendChild(th);
    });

    // Loop through the event details array and create table rows with "Register" buttons
    eventList.forEach((event) => {
      const row = eventTable.insertRow();
      row.insertCell().textContent = event.id;
      row.insertCell().textContent = event.eventName;
      row.insertCell().textContent = event.eventDescription;
      row.insertCell().textContent = event.eventDuration;
      row.insertCell().textContent = event.eventLocation;
      row.insertCell().textContent = event.eventFees;
      row.insertCell().textContent = event.eventMaxParticipants;
      row.insertCell().textContent = event.eventTags;

      const registerButtonCell = row.insertCell();
      const registerButton = document.createElement("button");
      registerButton.textContent = "Register";
      registerButton.setAttribute("data-event-id", event.id);
      registerButtonCell.appendChild(registerButton);
    });
  }

  // Event delegation using a parent element (eventTable) to listen for button clicks
  eventTable.addEventListener("click", function (event) {
    if (
      event.target.tagName === "BUTTON" &&
      event.target.textContent === "Register"
    ) {
      const eventId = event.target.getAttribute("data-event-id");
      if (eventId) {
        // Make a request to the backend API to register for the event based on the event ID
        fetch("http://localhost:8080/user/registerEvent?eventId=" + eventId, {
          method: "POST",
        })
          .then((response) => response.json())
          .then((data) => {
            alert("Event Registered");
            const eventDataString = encodeURIComponent(
              JSON.stringify(data.listData)
            );
            const eventData = JSON.parse(decodeURIComponent(eventDataString));
            console.log(eventData);
            populateEventTable(eventData);
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }
    }
  });
});
