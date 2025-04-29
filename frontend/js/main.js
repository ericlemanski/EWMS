// main.js (handles tab switching only)
function showTab(tabId) {
  document.querySelectorAll('.tab-content').forEach(tab => {
    tab.style.display = tab.id === tabId ? 'block' : 'none';
  });

  document.querySelectorAll('.tabs button').forEach(btn => {
    const matches = btn.getAttribute('onclick')?.includes(`'${tabId}'`);
    btn.classList.toggle('active', matches);
  });

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
