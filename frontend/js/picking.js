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
  