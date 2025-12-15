import React, { useState } from 'react';
import { X, Stethoscope, Calendar, Clock, User } from 'lucide-react';

const AddAppointmentForm = ({ onClose, onSave }) => {
  const [formData, setFormData] = useState({
    medico: '',
    especialidade: '',
    data: '',
    horario: ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    // Evita salvar se estiver vazio
    if (!formData.data || !formData.medico) return;

    // Formatar a data para exibição (ex: 2025-10-18 -> 18 Out)
    const dataObj = new Date(formData.data);
    // Ajuste do fuso horário para não pegar o dia anterior
    const userTimezoneOffset = dataObj.getTimezoneOffset() * 60000;
    const dataCorrigida = new Date(dataObj.getTime() + userTimezoneOffset);

    const dia = dataCorrigida.getDate();
    const meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
    const mes = meses[dataCorrigida.getMonth()];

    const consultaFormatada = {
      ...formData,
      dataFormatada: `${dia} ${mes}`
    };

    onSave(consultaFormatada);
    onClose();
  };

  return (
    <div className="fixed inset-0 bg-black/60 flex items-end sm:items-center justify-center z-50 p-4 animate-in fade-in">
      <div className="bg-white w-full max-w-md rounded-t-3xl sm:rounded-3xl p-6 shadow-2xl">

        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-bold text-gray-800">Nova Consulta</h2>
          <button onClick={onClose} className="p-2 bg-gray-100 rounded-full hover:bg-gray-200">
            <X className="w-5 h-5 text-gray-600" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-5">

          {/* Nome do Médico */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Nome do Médico(a)</label>
            <div className="relative">
              {/* Adicionei 'pointer-events-none' para o ícone não bloquear o clique */}
              <User className="absolute left-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" />
              <input
                required
                type="text"
                placeholder="Ex: Dr. Silva"
                className="w-full pl-10 p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-purple-500 placeholder-gray-400 text-gray-800"
                value={formData.medico}
                onChange={(e) => setFormData({ ...formData, medico: e.target.value })}
              />
            </div>
          </div>

          {/* Especialidade */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Especialidade</label>
            <div className="relative">
              <Stethoscope className="absolute left-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" />
              <input
                required
                type="text"
                placeholder="Ex: Cardiologista"
                className="w-full pl-10 p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-purple-500 placeholder-gray-400 text-gray-800"
                value={formData.especialidade}
                onChange={(e) => setFormData({ ...formData, especialidade: e.target.value })}
              />
            </div>
          </div>

          {/* Data e Hora */}
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Data</label>
              <div className="relative">
                <Calendar className="absolute left-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" />
                <input
                  required
                  type="date"
                  className="w-full pl-10 p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-purple-500 text-gray-800"
                  value={formData.data}
                  onChange={(e) => setFormData({ ...formData, data: e.target.value })}
                />
              </div>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Horário</label>
              <div className="relative">
                <Clock className="absolute left-3 top-3.5 w-5 h-5 text-gray-400 pointer-events-none" />
                <input
                  required
                  type="time"
                  className="w-full pl-10 p-3 bg-gray-50 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-purple-500 text-gray-800"
                  value={formData.horario}
                  onChange={(e) => setFormData({ ...formData, horario: e.target.value })}
                />
              </div>
            </div>
          </div>

          <button type="submit" className="w-full bg-purple-600 text-white font-bold py-4 rounded-xl hover:bg-purple-700 transition-colors shadow-lg shadow-purple-200">
            Agendar Consulta
          </button>

        </form>
      </div>
    </div>
  );
};

export default AddAppointmentForm;