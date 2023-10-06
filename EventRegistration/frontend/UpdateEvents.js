document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const eventDataString = urlParams.get("eventData");
  const eventData = JSON.parse(decodeURIComponent(eventDataString));
  console.log(eventData);

  document.getElementById("eventName").value = eventData.eventName;
  document.getElementById("eventDescription").value =
    eventData.eventDescription;
  document.getElementById("eventDuration").value = eventData.eventDuration;
  document.getElementById("eventLocation").value = eventData.eventLocation;
  document.getElementById("eventFees").value = eventData.eventFees;
  document.getElementById("eventMaxParticipants").value =
    eventData.eventMaxParticipants;
  document.getElementById("eventTags").value = eventData.eventTags;

  const editForm = document.getElementById("editForm");
  editForm.addEventListener("submit", function (event) {
    event.preventDefault();

    var updatedEventData = {
      eventName: document.getElementById("eventName").value,
      eventDescription: document.getElementById("eventDescription").value,
      eventDuration: document.getElementById("eventDuration").value,
      eventLocation: document.getElementById("eventLocation").value,
      eventFees: parseFloat(document.getElementById("eventFees").value),
      eventMaxParticipants: parseInt(
        document.getElementById("eventMaxParticipants").value
      ),
      eventTags: document.getElementById("eventTags").value,
    };

    const eventId = eventData.id;
    console.log(eventId);

    fetch("http://localhost:8080/event/updateEvent?eventId=" + eventId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedEventData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.status === 200) {
          alert("Event Data Updated Successfully");
          window.location.href = "MyEvents.html";
        } else if (data.status === 404) {
          alert("User Not Registered");
          alert("Event Data Not Updated");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });
});
