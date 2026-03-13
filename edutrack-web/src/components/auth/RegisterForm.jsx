import { useState } from 'react'

function RegisterForm() {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault()
    console.log('Nombre:', name)
    console.log('Email:', email)
    console.log('Password:', password)
    console.log('Confirmar Password:', confirmPassword)
  }

  return (
    <div className="min-h-screen bg-[#E6F4FC] flex items-center justify-center px-4">
      <div className="bg-white rounded-2xl p-10 w-full max-w-sm shadow-sm my-8">

        <h2 className="text-[#1A1A2E] text-2xl font-semibold text-center mb-1">
          Crear Cuenta
        </h2>
        <p className="text-gray-400 text-sm text-center mb-8">
          Regístrate en EduTrack
        </p>

        <form onSubmit={handleSubmit} className="flex flex-col gap-5">
          <div>
            <label className="block text-sm font-medium text-[#1A1A2E] mb-1.5">
              Nombre completo
            </label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Tu nombre completo"
              className="w-full px-4 py-3 rounded-xl border border-[#B5D4F4] text-sm text-[#1A1A2E] outline-none focus:border-[#178DD4]"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-[#1A1A2E] mb-1.5">
              Email
            </label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="tucorreo@email.com"
              className="w-full px-4 py-3 rounded-xl border border-[#B5D4F4] text-sm text-[#1A1A2E] outline-none focus:border-[#178DD4]"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-[#1A1A2E] mb-1.5">
              Contraseña
            </label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Tu contraseña"
              className="w-full px-4 py-3 rounded-xl border border-[#B5D4F4] text-sm text-[#1A1A2E] outline-none focus:border-[#178DD4]"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-[#1A1A2E] mb-1.5">
              Confirmar contraseña
            </label>
            <input
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              placeholder="Repite tu contraseña"
              className="w-full px-4 py-3 rounded-xl border border-[#B5D4F4] text-sm text-[#1A1A2E] outline-none focus:border-[#178DD4]"
            />
          </div>

          <button
            type="submit"
            className="w-full py-3 bg-[#178DD4] hover:bg-[#0C5FA3] text-white rounded-xl text-sm font-semibold transition-colors"
          >
            Crear Cuenta
          </button>
        </form>

        <p className="text-center text-xs text-gray-400 mt-6">
          ¿Ya tienes cuenta?{' '}
          <a href="/login" className="text-[#178DD4] font-medium">
            Inicia sesión
          </a>
        </p>

      </div>
    </div>
  )
}

export default RegisterForm