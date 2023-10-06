document.addEventListener("DOMContentLoaded", function () {
  var count = 0;
  fetch("http://localhost:8080/event/myEvent")
    .then((response) => response.json())
    .then((events) => {
      const eventDataString = encodeURIComponent(
        JSON.stringify(events.listData)
      );
      const eventData = JSON.parse(decodeURIComponent(eventDataString));
      populateEventTable(eventData);
    });
  if (this.location.reload) {
    fetch("http://localhost:8080/event/myEvent")
      .then((response) => response.json())
      .then((events) => {
        const eventDataString = encodeURIComponent(
          JSON.stringify(events.listData)
        );
        const eventData = JSON.parse(decodeURIComponent(eventDataString));
        populateEventTable(eventData);
      });
  }
  //const urlParams = new URLSearchParams(window.location.search);
  // const eventDataString = urlParams.get("eventData");
  //var eventData = JSON.parse(decodeURIComponent(eventDataString));
  //const eventTable = document.getElementById("eventTable");
  // console.log(eventData);
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
      "Action",
    ];
    const headerRow = eventTable.insertRow();
    headers.forEach((headerText) => {
      const th = document.createElement("th");
      th.textContent = headerText;
      headerRow.appendChild(th);
    });
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
      const updateButtonCell = row.insertCell();
      const updateButton = document.createElement("button");
      updateButton.textContent = "Update";
      updateButton.setAttribute("data-event-id", event.id);
      updateButtonCell.appendChild(updateButton);
      const deleteButtonCell = row.insertCell();
      const deleteButton = document.createElement("button");
      deleteButton.textContent = "Delete";
      deleteButton.setAttribute("data-event-id", event.id);
      deleteButtonCell.appendChild(deleteButton);
    });
  }
  eventTable.addEventListener("click", function (event) {
    if (
      event.target.tagName === "BUTTON" &&
      event.target.textContent === "Update"
    ) {
      const eventId = event.target.getAttribute("data-event-id");
      if (eventId) {
        fetch("http://localhost:8080/event/fetchEventData?eventId=" + eventId)
          .then((response) => response.json())
          .then((data) => {
            const eventDataString = encodeURIComponent(
              JSON.stringify(data.data)
            );
            window.location.href = `UpdateEvents.html?eventData=${eventDataString}`;
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }
    }
  });
  eventTable.addEventListener("click", function (event) {
    if (
      event.target.tagName === "BUTTON" &&
      event.target.textContent === "Delete"
    ) {
      const eventId = event.target.getAttribute("data-event-id");
      if (eventId) {
        fetch("http://localhost:8080/event/deleteEvent?eventId=" + eventId, {
          method: "DELETE",
        })
          .then((response) => response.json())
          .then((data) => {
            if(data.status===200){
            alert("Event Data Deleted");
            const eventDataString = encodeURIComponent(JSON.stringify(data.listData));
            eventData = JSON.parse(decodeURIComponent(eventDataString));
            populateEventTable(eventData);
            }
            else{
              alert("User Already Registered Unable to delete Event");
              location.reload;
            }
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }
    }
  });
});
