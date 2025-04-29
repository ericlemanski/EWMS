async function loadShipments() {
    try {
      const res = await fetch("http://localhost:4567/shipments");
      const data = await res.json();
      const container = document.getElementById("shipment-list");
      container.innerHTML = "";
      data.forEach(ship => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `
          <strong>${ship.ship}</strong><br/>
          Carrier: ${ship.car}<br/>
          Status: ${ship.shipsts}
          <div style="float:right;">Dock: ${ship.dock}</div>
        `;
        container.appendChild(div);
      });
    } catch (err) {
      console.error("Error loading shipments:", err);
    }
  }
  