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
  