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
  