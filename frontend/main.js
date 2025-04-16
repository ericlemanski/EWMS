function showTab(tabId) {
  // Hide all tab contents
  document.querySelectorAll('.tab-content').forEach(tab => {
    tab.style.display = tab.id === tabId ? 'block' : 'none';
  });

  // Update active button styling
  document.querySelectorAll('.tabs button').forEach(btn => {
    const matches = btn.getAttribute('onclick')?.includes(`'${tabId}'`);
    btn.classList.toggle('active', matches);
  });

  // Handle tab-specific logic
  if (tabId === 'inventory') {
    showInventoryTab('locations');
  } else if (tabId === 'shipments') {
    loadShipments();
  } else if (tabId === 'receiving') {
    loadReceiving();
  } else if (tabId === 'picking') {
    loadPicking();
  } else if (tabId === 'packing') {
    loadPacking();
  } else if (tabId === 'outbound') {
    loadOutbound();
  } else if (tabId === 'production') {
    loadProduction();
  }
}

window.onload = () => {
  showTab("dashboard");
};

function showInventoryTab(subTabId) {
  document.querySelectorAll('.inventory-sub-tab').forEach(tab => {
    tab.style.display = 'none';
  });

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

async function loadReceiving() {
  try {
    const res = await fetch("http://localhost:4567/receiving/rcv");
    const data = await res.json();
    const container = document.getElementById("receiving-list");
    container.innerHTML = "";

    data.forEach(rcv => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `
        <strong>RCV ID:</strong> ${rcv.rcv_id}<br/>
        Warehouse: ${rcv.wh}<br/>
        Treated Req: ${rcv.treq}<br/>
        Status: ${rcv.sts}
      `;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading receiving data:", err);
  }
}

async function loadPicking() {
  try {
    const res = await fetch("http://localhost:4567/picking");
    const data = await res.json();
    const container = document.getElementById("picking-list");
    container.innerHTML = "";

    data.forEach(pick => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `
        <strong>PCK ID:</strong> ${pick.pck_id}<br/>
        Qty: ${pick.qty}<br/>
        Warehouse: ${pick.wh}<br/>
        Pick Work: ${pick.pckwrk}
      `;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading picking data:", err);
  }
}

async function loadPacking() {
  try {
    const res = await fetch("http://localhost:4567/packing");
    const data = await res.json();
    const container = document.getElementById("packing-list");
    container.innerHTML = "";

    data.forEach(pack => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `
        <strong>Pack:</strong> ${pack.pack}<br/>
        Zone: ${pack.packzon_id}<br/>
        WH: ${pack.wh}<br/>
        Work ID: ${pack.packwrk_id}
      `;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading packing data:", err);
  }
}

async function loadOutbound() {
  try {
    const res = await fetch("http://localhost:4567/outbound");
    const data = await res.json();
    const container = document.getElementById("outbound-list");
    container.innerHTML = "";

    data.forEach(order => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `
        <strong>Order ID:</strong> ${order.ord_id}<br/>
        WH: ${order.wh}<br/>
        Treated Req: ${order.treq}
      `;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading outbound data:", err);
  }
}

async function loadProduction() {
  try {
    const res = await fetch("http://localhost:4567/production");
    const data = await res.json();
    const container = document.getElementById("production-list");
    container.innerHTML = "";

    data.forEach(prod => {
      const div = document.createElement("div");
      div.className = "card";
      div.innerHTML = `
        <strong>Prod ID:</strong> ${prod.prod_id}<br/>
        WH: ${prod.wh}<br/>
        Status: ${prod.sts}
      `;
      container.appendChild(div);
    });
  } catch (err) {
    console.error("Error loading production data:", err);
  }
}
