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
  