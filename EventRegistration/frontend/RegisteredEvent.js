document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const eventDataString = urlParams.get('eventData');
    const eventData = JSON.parse(decodeURIComponent(eventDataString));
    console.log(eventData)
    const eventTable = document.getElementById("eventTable");

    populateEventTable(eventData);

    function populateEventTable(eventList) {
        eventTable.innerHTML = ""; 


        const headers = ["Event Id", "Event Name", "Event Description", "Event Duration", "Event Location", "Event Fees", "Event Max Participants", "Event Tags"];
        const headerRow = eventTable.insertRow();
        headers.forEach(headerText => {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        eventList.forEach(event => {
            const row = eventTable.insertRow();
            row.insertCell().textContent = event.id;
            row.insertCell().textContent = event.eventName;
            row.insertCell().textContent = event.eventDescription;
            row.insertCell().textContent = event.eventDuration;
            row.insertCell().textContent = event.eventLocation;
            row.insertCell().textContent = event.eventFees;
            row.insertCell().textContent = event.eventMaxParticipants;
            row.insertCell().textContent = event.eventTags;
        });
    }
    });

