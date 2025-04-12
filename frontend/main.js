function showTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(tab => {
    tab.style.display = tab.id === tabId ? 'block' : 'none';
  });

  if (tabId === 'inventory') {
    showInventoryTab('locations'); // default sub-tab
  } else if (tabId === 'shipments') {
    loadShipments();
  }
}

function showInventoryTab(subTabId) {
  document.querySelectorAll('.inventory-sub-tab').forEach(tab => {
    tab.style.display = 'none';
  });

  // Show the correct list container
  document.getElementById("location-list").style.display = 'none';
  document.getElementById("item-list").style.display = 'none';
  document.getElementById("lpn-list").style.display = 'none';

  if (subTabId === 'locations') {
    document.getElementById("location-list").style.display = 'flex';
    loadLocations();
  } else if (subTabId === 'items') {
    document.getElementById("item-list").style.display = 'flex';
    loadItems();
  } else if (subTabId === 'lpns') {
    document.getElementById("lpn-list").style.display = 'flex';
    loadLPNs();
  }
}

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

async function loadItems() {
  try {
    const res = await fetch("http://localhost:4567/items");
    const data = await res.json();
    const container = document.getElementById("item-list");
    container.innerHTML = "";

    data.forEach(item => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `<strong>Item:</strong> ${item.item}`;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading items:", err);
  }
}


async function loadLPNs() {
  try {
    const res = await fetch("http://localhost:4567/lpns");
    const data = await res.json();
    const container = document.getElementById("lpn-list");
    container.innerHTML = "";

    data.forEach(lpn => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `<strong>LPN:</strong> ${lpn.lpn}<br/><strong>Qty:</strong> ${lpn.qty}`;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading LPNs:", err);
  }
}


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

window.onload = () => {
  showTab("inventory");
};
