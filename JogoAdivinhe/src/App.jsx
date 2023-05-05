import React, {useState, useRef} from 'react'

import Header from './Header'

let numeroAleatorio = Math.floor((Math.random() * 100) + 1)

export default function App() {
  const [tentativas, setTentativas] = useState(10)
  const [fimDoJogo, setFimDoJogo] = useState(false)
  const [palpites, setPalpites] = useState('')
  const [msgErro, setMsgErro] = useState('')
  const [resultado, setResultado] = useState('')
  const textInput = useRef(null)
  
  const validarPalpite = () => {
    const value = Number(textInput.current.value)

    if(value < 1 || value > 100) {
      setMsgErro('Valor é inválido.')
      return
    }

    if(value === numeroAleatorio) {
      setResultado('Parabéns!!! Você ganhou.')
      setFimDoJogo(true)
    } else if(tentativas > 0) {
      setTentativas(tentativas - 1)

      if(value > numeroAleatorio)
        setResultado('Seu palpite está muito alto')
      else 
        setResultado('Seu palpite está muito baixo')
      
      setPalpites(`${palpites},${value}`)
    } else {
      setFimDoJogo(true)
      setResultado('FIM DO JOGO!')
    }
  }

  const reiniciar = () => {
    numeroAleatorio = Math.floor((Math.random() * 100) + 1)
    setTentativas(10)
    setPalpites('')
    setMsgErro('')
    setResultado('')
    setFimDoJogo(false)
    inputText.current.value = ''
  }
  
  return (
    <>
      <Header tent={tentativas}/>
    
      <label>Digite seu palpite:</label>
      <input 
        ref={textInput}
        type='number' 
        min={1} 
        max={100} 
        disabled={fimDoJogo}
      />
      <button 
        onClick={validarPalpite}
        disabled={fimDoJogo}
      >
        Enviar palpite
      </button>
      {
        fimDoJogo && <button>Reinicar</button>
      }
      <p>{palpites}</p>
      <p>{msgErro}</p>
      <p>{resultado}</p>
    </>
  )
}