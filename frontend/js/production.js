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
  