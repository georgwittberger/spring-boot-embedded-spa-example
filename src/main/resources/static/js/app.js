document.addEventListener('DOMContentLoaded', async () => {
    const contextPath = document.querySelector('meta[name="app-context-path"]').getAttribute('content');
    const text = await fetch(`${contextPath}/api/text`).then(response => response.text());
    document.querySelector('#app').innerHTML = `<h1>${text}</h1>`;
});
