import React from 'react'

export default function Header(props) {
  return (
    <>
      <h1>Jogo adivinhe um número em {props.tent} tentativas</h1>
      <p>
        Selecionamos um número aleatório entre 1 e 100. Veja se 
        consegue adivinhar em 10 chances ou menos. Lhe diremos
        se seu palpite está com valor alto ou baixo.
      </p>
    </>
  )
}