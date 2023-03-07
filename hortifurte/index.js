
  // Variáveis para o carrinho e o botão de mostrar/ocultar
  const cart = document.querySelector('#cart');
  const cartItems = document.querySelector('#cart-items');
  const cartTotal = document.querySelector('#cart-total');
  const cartToggle = document.querySelector('#cart-toggle');

  // Adicionar evento de clique aos botões "Adicionar ao carrinho"
  const abobrinhaAddBtn = document.querySelector('#abobrinha-add');
  abobrinhaAddBtn.addEventListener('click', () => {
    addItemToCart('Abobrinha', 2.5);
  });

  const cebolaAddBtn = document.querySelector('#cebola-add');
  cebolaAddBtn.addEventListener('click', () => {
    addItemToCart('Cebola', 3);
  });

  // Função para adicionar um item ao carrinho
  function addItemToCart(name, price) {
    const newItem = document.createElement('li');
    newItem.textContent = `${name} - R$ ${price.toFixed(2)}`;
    cartItems.appendChild(newItem);

    const total = calculateCartTotal();
    cartTotal.textContent = `Total: R$ ${total.toFixed(2)}`;
  }

  // Função para calcular o total do carrinho
  function calculateCartTotal() {
    let total = 0;
    cartItems.childNodes.forEach(item => {
      const price = parseFloat(item.textContent.split('R$ ')[1]);
      total += price;
    });
    return total;
  }

  // Adicionar evento de clique ao botão de mostrar/ocultar
  cartToggle.addEventListener('click', () => {
    cart.classList.toggle('hidden');
  });


