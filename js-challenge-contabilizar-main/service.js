const validarEntradaDeDados = (lancamento) => {
   const { cpf, valor } = lancamento

   // CPF deve conter apenas caracteres numéricos.
   if (!/^\d+$/.test(cpf)) {
      return 'CPF deve conter apenas caracteres numéricos.'
   }

   // verificar se os digitos sao validos.
   const isValidCPF = (cpf) => {
      let sum = 0, rest
      if (cpf === '00000000000') {
         return false
      }
      for (let i = 1; i <= 9; i++) {
         sum += parseInt(cpf.substring(i - 1, i)) * (11 - i)
      }


      rest = (sum * 10) % 11
      if ((rest === 10) || (rest === 11)) {
         rest = 0
      }
      if (rest !== parseInt(cpf.substring(9, 10))) {
         return false
      }

      sum = 0
      for (let i = 1; i <= 10; i++) {
         sum += parseInt(cpf.substring(i - 1, i)) * (12 - i)
      }

      rest = (sum * 10) % 11
      if ((rest === 10) || (rest === 11)) {
         rest = 0
      }
      if (rest !== parseInt(cpf.substring(10, 11))) {
         return false
      }

      return true
   }

   if (!isValidCPF(cpf)) {
      return 'CPF inválido.'
   }

   if (!/^-?\d+(\.\d{1,2})?$/.test(valor)) {
      return 'Valor deve ser numérico e ter até duas casas decimais.'
   }

   // arrendoda para casas decimais
   const valorArredondado = Number(valor).toFixed(2);

   
   if (valorArredondado > 15000) {
      return 'Valor não pode ser superior a 15000,00.'
   }

 
   if (valorArredondado < -2000) {
      return 'Valor não pode ser inferior a -2000,00.'
   }

  
   return null
}

const recuperarSaldosPorConta = (lancamentos) => {
   const saldos = {} //objeto para armazenar saldos

   for (const lancamento of lancamentos) {
      const { cpf, valor } = lancamento // pegar valor e CPF 

      if (!saldos[cpf]) {
         saldos[cpf] = valor // se não existir, pegar o valor do lançamento como saldo inicial da conta
      } else {
         saldos[cpf] += valor // se ja existir, adiciona o valor do lançamento ao saldo atual da conta
      }
   }

   return Object.keys(saldos).map((cpf) => ({ cpf, valor: saldos[cpf] })) // fazer um array de objetos com o CPF e o saldo final de cada conta
}




const recuperarMaiorMenorLancamentos = (cpf, lancamentos) => {
   // pegar os lançamentos pelo CPF recebido como parâmetro
   const lancamentosCPF = lancamentos.filter(lancamento => lancamento.cpf === cpf);

   // lançamentos pelo valor, do menor para o maior
   lancamentosCPF.sort((a, b) => a.valor - b.valor);

   // array de saída com os dois lançamentos (maior e menor)
   const lancamentosSaida = [];

   if (lancamentosCPF.length === 0) {
      // se  não tiver lançamentos para o CPF, retorna um array vazio
      return lancamentosSaida;
   } else if (lancamentosCPF.length === 1) {
      // se tiver apenas um lançamento para o CPF, adiciona o mesmo lançamento duas vezes no array de saída
      lancamentosSaida.push(lancamentosCPF[0]);
      return lancamentosSaida;
   } else {
      // se tiver dois ou mais lançamentos para o CPF, adiciona o menor e o maior lançamento no array de saída
      lancamentosSaida.push(lancamentosCPF[0]);
      lancamentosSaida.push(lancamentosCPF[lancamentosCPF.length - 1]);
      return lancamentosSaida;
   }
}
const recuperarMaioresSaldos = (lancamentos, n = 3) => {
   const saldos = recuperarSaldosPorConta(lancamentos)  // pega a função "recuperarSaldosPorConta" para obter os saldos das contas


   const maioresSaldos = saldos
      .filter(({ valor }) => !isNaN(valor)) // filtra as contas que não possuem valor de saldo (NaN)
      .sort((a, b) => b.valor - a.valor)// ordena os saldos em ordem decrescente
      .slice(0, n) // Retorna um array com os "n" maiores saldos das contas

   return maioresSaldos
}

const recuperarMaioresMedias = (lancamentos) => {
   // junta os valores dos lançamentos por CPF
   const lancamentosPorCpf = lancamentos.reduce((acc, lancamento) => {
      if (!acc[lancamento.cpf]) {
         acc[lancamento.cpf] = [];
      }
      acc[lancamento.cpf].push(lancamento.valor);
      return acc;
   }, {});

   // clcula a media de cada CPF
   const mediasPorCpf = Object.keys(lancamentosPorCpf).reduce((acc, cpf) => {
      const lancamentosCpf = lancamentosPorCpf[cpf];
      const total = lancamentosCpf.reduce((acc, valor) => acc + valor, 0);
      const media = total / lancamentosCpf.length;
      acc[cpf] = media;
      return acc;
   }, {});

   // ordena as medias de forma decrescente
   const cpfOrdenados = Object.keys(mediasPorCpf).sort((a, b) => mediasPorCpf[b] - mediasPorCpf[a]);

   // Retorna os tres maiores saldos médios
   return cpfOrdenados.slice(0, 3).map((cpf) => ({ cpf, valor: mediasPorCpf[cpf].toFixed(3) }));
};
  