function showTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(tab => {
    tab.style.display = tab.id === tabId ? 'block' : 'none';
  });

  if (tabId === 'inventory') loadLocations();
  if (tabId === 'shipments') loadShipments();
}

  
  // Fetch and display locations from backend
  async function loadLocations() {
    try {
      const res = await fetch("http://localhost:4567/locations");
      const data = await res.json();
      const container = document.getElementById("location-list");
      container.innerHTML = "";
  
      data.forEach(loc => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `<strong>${loc.location}</strong><br/>Area: ${loc.area}`;
        container.appendChild(div);
      });
    } catch (err) {
      console.error("Error loading locations:", err);
    }
  }
  
  // Load on start
  window.onload = () => {
    showTab("inventory");
    loadLocations();
  };

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
          Status: ${ship.shipsts}<br/>
          Dock: ${ship.dock}
        `;
        container.appendChild(div);
      });
    } catch (err) {
      console.error("Error loading shipments:", err);
    }
  }
  
  