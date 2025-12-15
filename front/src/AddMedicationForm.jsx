import React, { useState } from 'react';
import { X, Pill, Syringe, Clock, Calendar, Droplets, GlassWater, Wind } from 'lucide-react';

const AddMedicationForm = ({ onClose, onSave }) => {
  const [formData, setFormData] = useState({
    nome: '',
    dosagem: '',
    horario: '',
    tipo: 'pill'
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(formData);
    onClose();
  };

  // Lista de tipos disponíveis para facilitar a criação dos botões
  const tiposMedicamento = [
    { id: 'pill', label: 'Compr.', icon: <Pill className="w-5 h-5" /> },
    { id: 'injection', label: 'Injeção', icon: <Syringe className="w-5 h-5" /> },
    { id: 'drops', label: 'Gotas', icon: <Droplets className="w-5 h-5" /> },
    { id: 'liquid', label: 'Líquido', icon: <GlassWater className="w-5 h-5" /> },
    { id: 'inhaler', label: 'Inalador', icon: <Wind className="w-5 h-5" /> },
  ];

  return (
    <div className="fixed inset-0 bg-black/60 flex items-end sm:items-center justify-center z-50 p-4 animate-in fade-in">
      <div className="bg-white w-full max-w-md rounded-t-3xl sm:rounded-3xl p-6 shadow-2xl">

        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-bold text-gray-800">Novo Medicamento</h2>
          <button onClick={onClose} className="p-2 bg-gray-100 rounded-full hover:bg-gray-200">
            <X className="w-5 h-5 text-gray-600" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-5">

          {/* Nome */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Nome do medicamento</label>
            <input
              required
              type="text"
              placeholder="Ex: Dipirona"
              className="w-full p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-800"
              value={formData.nome}
              onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
            />
          </div>

          {/* Dosagem e Horário */}
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Dosagem</label>
              <input
                required
                type="text"
                placeholder="Ex: 20 gotas"
                className="w-full p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-800"
                value={formData.dosagem}
                onChange={(e) => setFormData({ ...formData, dosagem: e.target.value })}
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Horário</label>
              <div className="relative">
                <Clock className="absolute left-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" />
                <input
                  required
                  type="time"
                  className="w-full pl-10 p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-800"
                  value={formData.horario}
                  onChange={(e) => setFormData({ ...formData, horario: e.target.value })}
                />
              </div>
            </div>
          </div>

          {/* Tipo de Medicamento (Agora com Grid de 3 colunas) */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">Tipo de Aplicação</label>
            <div className="grid grid-cols-3 gap-2">
              {tiposMedicamento.map((tipo) => (
                <button
                  key={tipo.id}
                  type="button"
                  onClick={() => setFormData({ ...formData, tipo: tipo.id })}
                  className={`p-3 rounded-xl border flex flex-col items-center justify-center gap-1 transition-all ${formData.tipo === tipo.id ? 'bg-blue-50 border-blue-500 text-blue-700 font-bold' : 'border-gray-200 text-gray-500 hover:bg-gray-50'}`}
                >
                  {tipo.icon}
                  <span className="text-xs">{tipo.label}</span>
                </button>
              ))}
            </div>
          </div>

          <button type="submit" className="w-full bg-blue-600 text-white font-bold py-4 rounded-xl hover:bg-blue-700 transition-colors shadow-lg shadow-blue-200">
            Salvar Medicamento
          </button>

        </form>
      </div>
    </div>
  );
};

export default AddMedicationForm;