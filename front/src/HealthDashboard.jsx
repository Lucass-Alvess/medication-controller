import React, { useState } from 'react';
import { Pill, Calendar, Clock, CheckCircle, AlertCircle, Menu, Bell, Plus, Syringe, Stethoscope, X, ChevronRight, User, ShieldAlert, Settings, LogOut, Edit2, Save, Droplets, GlassWater, Wind, Trash2, Check } from 'lucide-react';
import AddMedicationForm from './AddMedicationForm';
import AddAppointmentForm from './AddAppointmentForm';

const HealthDashboard = ({ onLogout }) => {
  const [activeTab, setActiveTab] = useState('hoje');
  const [modalMode, setModalMode] = useState('none');
  const [isEditingProfile, setIsEditingProfile] = useState(false);

  // --- ESTADO DAS NOTIFICAÇÕES ---
  const [showNotifications, setShowNotifications] = useState(false);
  const [notificacoes, setNotificacoes] = useState([
    { id: 1, titulo: 'Atenção: Atraso', msg: 'Você esqueceu a Losartana das 08:00!', tipo: 'erro', lida: false, hora: 'há 2h' },
    { id: 2, titulo: 'Consulta Amanhã', msg: 'Dr. Silva (Cardiologista) às 15:30', tipo: 'aviso', lida: false, hora: 'há 30min' },
    { id: 3, titulo: 'Estoque Baixo', msg: 'Sua Metformina acaba em 3 dias.', tipo: 'alerta', lida: true, hora: 'ontem' },
  ]);

  // Conta quantas não foram lidas para a bolinha vermelha
  const naoLidas = notificacoes.filter(n => !n.lida).length;

  const marcarTodasComoLidas = () => {
    setNotificacoes(notificacoes.map(n => ({ ...n, lida: true })));
  };

  const limparNotificacao = (id) => {
    setNotificacoes(notificacoes.filter(n => n.id !== id));
  };

  // --- RESTO DOS DADOS E FUNÇÕES ---
  const [userProfile, setUserProfile] = useState({
    nome: 'Maria da Silva',
    idade: '68',
    tipoSanguineo: 'O+',
    altura: '1.65',
    peso: '72',
    alergias: 'Dipirona, Penicilina',
    planoSaude: 'Unimed - 999888'
  });

  const handleSaveProfile = () => setIsEditingProfile(false);

  const dataAtual = new Date();
  const opcoesData = { weekday: 'long', day: 'numeric', month: 'short' };
  const dataFinal = dataAtual.toLocaleDateString('pt-PT', opcoesData).replace('.', '').replace(/-feira/, '').replace(/^\w/, (c) => c.toUpperCase());

  const [medicamentos, setMedicamentos] = useState([
    { id: 1, nome: 'Losartana', dosagem: '50mg', horario: '08:00', status: 'tomado', tipo: 'pill' },
    { id: 2, nome: 'Xarope', dosagem: '10ml', horario: '14:00', status: 'pendente', tipo: 'liquid' },
  ]);

  const [consultas, setConsultas] = useState([
    { id: 1, medico: 'Dr. Silva', especialidade: 'Cardiologista', data: '18 Out', horario: '15:30' }
  ]);

  const adicionarMedicamento = (novo) => {
    const itemComId = { ...novo, id: Date.now(), status: 'pendente' };
    setMedicamentos([...medicamentos, itemComId]);
    setModalMode('none');
  };

  const adicionarConsulta = (nova) => {
    const consultaComId = { id: Date.now(), medico: nova.medico, especialidade: nova.especialidade, data: nova.dataFormatada, horario: nova.horario };
    setConsultas([...consultas, consultaComId]);
    setModalMode('none');
  };

  const toggleStatus = (id) => setMedicamentos(medicamentos.map(m => m.id === id ? { ...m, status: m.status === 'pendente' ? 'tomado' : 'pendente' } : m));

  const getIconeMedicamento = (tipo, className) => {
    switch (tipo) {
      case 'pill': return <Pill className={className} />;
      case 'injection': return <Syringe className={className} />;
      case 'drops': return <Droplets className={className} />;
      case 'liquid': return <GlassWater className={className} />;
      case 'inhaler': return <Wind className={className} />;
      default: return <Pill className={className} />;
    }
  };

  // --- RENDERIZADORES ---
  const renderHoje = () => (
    <div className="space-y-8 animate-in fade-in duration-500">
      <section>
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-bold text-gray-800">Próxima Dose</h2>
          <span className="text-blue-600 text-sm font-semibold bg-blue-50 px-3 py-1 rounded-full">Hoje</span>
        </div>
        <div className="bg-blue-600 rounded-2xl p-6 text-white shadow-lg shadow-blue-200">
          <div className="flex justify-between items-start">
            <div><p className="text-blue-100 text-sm mb-1">Horário agendado</p><div className="flex items-center gap-2"><Clock className="w-6 h-6" /><span className="text-4xl font-bold">14:00</span></div></div>
            <div className="bg-white/20 p-3 rounded-full backdrop-blur-sm"><GlassWater className="w-8 h-8 text-white" /></div>
          </div>
          <div className="mt-6"><h3 className="text-2xl font-bold">Xarope Tosse</h3><p className="text-blue-100">10ml • Após almoço</p></div>
        </div>
      </section>

      <section>
        <h2 className="text-lg font-bold text-gray-800 mb-4">Cronograma de Hoje</h2>
        <div className="space-y-4">
          {medicamentos.length === 0 ? <p className="text-gray-400 text-center">Tudo em dia!</p> : medicamentos.map((med) => (
            <div key={med.id} onClick={() => toggleStatus(med.id)} className={`cursor-pointer transition-all flex items-center p-4 rounded-xl border ${med.status === 'tomado' ? 'bg-gray-50 border-gray-200 opacity-60' : 'bg-white border-gray-100 shadow-sm'}`}>
              <div className={`p-3 rounded-full mr-4 ${med.status === 'tomado' ? 'bg-green-100 text-green-600' : 'bg-blue-50 text-blue-500'}`}>
                {med.status === 'tomado' ? <CheckCircle className="w-6 h-6" /> : getIconeMedicamento(med.tipo, "w-6 h-6")}
              </div>
              <div className="flex-1"><h4 className={`font-bold ${med.status === 'tomado' ? 'line-through text-gray-500' : 'text-gray-800'}`}>{med.nome}</h4><p className="text-xs text-gray-500">{med.dosagem}</p></div>
              <div className="text-right"><span className="block font-bold text-gray-700">{med.horario}</span>{med.status === 'pendente' && <span className="text-xs text-orange-500 font-medium flex items-center justify-end gap-1"><AlertCircle className="w-3 h-3" /></span>}</div>
            </div>
          ))}
        </div>
      </section>
    </div>
  );

  const renderAgenda = () => (
    <div className="space-y-8 animate-in slide-in-from-right duration-500">
      <h2 className="text-2xl font-bold text-gray-800 mb-6">Sua Agenda</h2>
      <section>
        <h3 className="text-sm font-bold text-gray-500 uppercase tracking-wider mb-4">Próximas Consultas</h3>
        <div className="space-y-4">
          {consultas.length === 0 ? (
            <div className="bg-white p-8 rounded-2xl border border-dashed border-gray-300 text-center text-gray-400"><Calendar className="w-10 h-10 mx-auto mb-2 opacity-50" /><p>Nenhuma consulta futura</p></div>
          ) : (
            consultas.map((c) => (
              <div key={c.id} className="bg-white p-5 rounded-2xl border border-gray-100 shadow-md border-l-4 border-l-purple-500 flex items-center justify-between">
                <div className="flex items-center gap-4">
                  <div className="bg-purple-50 p-3 rounded-xl text-purple-600 flex flex-col items-center min-w-[60px]"><span className="text-xs font-bold uppercase">{c.data.split(' ')[1]}</span><span className="text-xl font-bold">{c.data.split(' ')[0]}</span></div>
                  <div><h3 className="font-bold text-gray-800 text-lg">{c.medico}</h3><p className="text-sm text-gray-500">{c.especialidade}</p></div>
                </div>
                <div className="text-right"><span className="flex items-center gap-1 text-sm font-bold text-gray-600 bg-gray-100 px-3 py-1 rounded-lg"><Clock className="w-3 h-3" /> {c.horario}</span></div>
              </div>
            ))
          )}
        </div>
      </section>
    </div>
  );

  const renderPerfil = () => (
    <div className="space-y-6 animate-in slide-in-from-right duration-500">
      <div className="relative bg-white p-6 rounded-3xl shadow-sm border border-gray-100 flex flex-col items-center text-center">
        <button onClick={() => isEditingProfile ? handleSaveProfile() : setIsEditingProfile(true)} className={`absolute top-4 right-4 p-2 rounded-full ${isEditingProfile ? 'bg-green-100 text-green-600' : 'bg-gray-100 text-gray-600'}`}>{isEditingProfile ? <Save className="w-5 h-5" /> : <Edit2 className="w-5 h-5" />}</button>
        <div className="w-24 h-24 bg-blue-100 rounded-full flex items-center justify-center text-blue-600 border-4 border-white shadow-md mb-4"><User className="w-10 h-10" /></div>
        {isEditingProfile ? (
          <div className="w-full space-y-3"><input className="w-full text-center font-bold text-xl border-b border-blue-300 focus:outline-none" value={userProfile.nome} onChange={e => setUserProfile({ ...userProfile, nome: e.target.value })} /><input className="w-full text-center text-gray-500 text-sm border-b border-blue-300 focus:outline-none" value={userProfile.planoSaude} onChange={e => setUserProfile({ ...userProfile, planoSaude: e.target.value })} /></div>
        ) : (
          <div><h2 className="text-2xl font-bold text-gray-800">{userProfile.nome}</h2><p className="text-gray-500">{userProfile.idade} anos • {userProfile.planoSaude}</p></div>
        )}
      </div>

      <div className="grid grid-cols-3 gap-3">
        {['tipoSanguineo', 'peso', 'altura'].map((key) => (
          <div key={key} className="bg-blue-50 p-4 rounded-2xl flex flex-col items-center justify-center text-blue-800">
            <span className="text-xs uppercase font-bold opacity-70 mb-1">{key.replace('tipo', '').replace('Sanguineo', 'Tipo')}</span>
            {isEditingProfile ? <input className="w-full text-center bg-white/50 rounded p-1 font-bold" value={userProfile[key]} onChange={e => setUserProfile({ ...userProfile, [key]: e.target.value })} /> : <span className="text-xl font-bold">{userProfile[key]}</span>}
          </div>
        ))}
      </div>

      <div className="bg-white rounded-3xl p-6 shadow-sm border border-gray-100">
        <h3 className="text-lg font-bold text-gray-800 mb-4 flex items-center gap-2"><ShieldAlert className="w-5 h-5 text-red-500" /> Alergias</h3>
        {isEditingProfile ? <textarea className="w-full p-2 border border-gray-200 rounded-lg text-sm" value={userProfile.alergias} onChange={e => setUserProfile({ ...userProfile, alergias: e.target.value })} /> : <div className="flex flex-wrap gap-2">{userProfile.alergias.split(',').map((a, i) => <span key={i} className="bg-red-50 text-red-600 px-3 py-1 rounded-full text-sm border border-red-100">{a}</span>)}</div>}
      </div>

      <div className="bg-white rounded-3xl shadow-sm border border-gray-100 overflow-hidden">
        <button className="w-full flex items-center justify-between p-5 hover:bg-gray-50 transition-colors text-left border-b border-gray-100"><div className="flex items-center gap-3 text-gray-700"><Settings className="w-5 h-5" /> Configurações</div></button>
        <button onClick={onLogout} className="w-full flex items-center justify-between p-5 hover:bg-red-50 transition-colors text-left group"><div className="flex items-center gap-3 text-red-600"><div className="bg-red-100 p-2 rounded-lg group-hover:bg-red-200"><LogOut className="w-5 h-5" /></div><span className="font-medium">Sair da Conta</span></div></button>
      </div>
    </div>
  );

  return (
    <div className="bg-gray-50 min-h-screen font-sans text-gray-800 pb-24 relative">
      <header className="bg-white p-6 shadow-sm flex justify-between items-center sticky top-0 z-10">
        <div><h1 className="text-2xl font-bold text-blue-600">MediControl</h1><p className="text-sm text-gray-500 capitalize">{activeTab === 'perfil' ? 'Meu Perfil' : dataFinal}</p></div>

        {/* --- CENTRAL DE NOTIFICAÇÕES (NOVO) --- */}
        <div className="relative">
          <button onClick={() => setShowNotifications(!showNotifications)} className="relative p-2 rounded-full hover:bg-gray-100 transition-colors">
            <Bell className={`w-6 h-6 ${naoLidas > 0 ? 'text-blue-600' : 'text-gray-400'}`} />
            {naoLidas > 0 && <span className="absolute top-1 right-1 bg-red-500 w-3 h-3 rounded-full border-2 border-white"></span>}
          </button>

          {/* DROPDOWN DE NOTIFICAÇÕES */}
          {showNotifications && (
            <>
              {/* Fundo invisível para fechar ao clicar fora */}
              <div className="fixed inset-0 z-10" onClick={() => setShowNotifications(false)}></div>

              <div className="absolute right-0 top-12 w-80 bg-white rounded-2xl shadow-2xl border border-gray-100 z-20 overflow-hidden animate-in fade-in slide-in-from-top-2">
                <div className="bg-blue-50 p-4 flex justify-between items-center border-b border-blue-100">
                  <h3 className="font-bold text-blue-900">Notificações</h3>
                  {naoLidas > 0 && <button onClick={marcarTodasComoLidas} className="text-xs text-blue-600 font-bold hover:underline">Marcar lidas</button>}
                </div>

                <div className="max-h-80 overflow-y-auto">
                  {notificacoes.length === 0 ? (
                    <div className="p-8 text-center text-gray-400"><Bell className="w-8 h-8 mx-auto mb-2 opacity-50" /><p className="text-sm">Sem notificações novas</p></div>
                  ) : (
                    notificacoes.map((notif) => (
                      <div key={notif.id} className={`p-4 border-b border-gray-50 flex gap-3 ${notif.lida ? 'bg-white opacity-60' : 'bg-blue-50/30'}`}>
                        <div className={`mt-1 w-2 h-2 rounded-full ${notif.tipo === 'erro' ? 'bg-red-500' : notif.tipo === 'alerta' ? 'bg-orange-500' : 'bg-blue-500'}`}></div>
                        <div className="flex-1">
                          <h4 className={`text-sm font-bold ${notif.tipo === 'erro' ? 'text-red-600' : 'text-gray-800'}`}>{notif.titulo}</h4>
                          <p className="text-xs text-gray-500 mt-1">{notif.msg}</p>
                          <span className="text-[10px] text-gray-400 font-medium mt-2 block uppercase">{notif.hora}</span>
                        </div>
                        <button onClick={() => limparNotificacao(notif.id)} className="text-gray-300 hover:text-red-500 h-fit"><X className="w-4 h-4" /></button>
                      </div>
                    ))
                  )}
                </div>
              </div>
            </>
          )}
        </div>
      </header>

      <main className="p-6">
        {activeTab === 'hoje' && renderHoje()}
        {activeTab === 'agenda' && renderAgenda()}
        {activeTab === 'perfil' && renderPerfil()}
      </main>

      {/* BOTÃO FLUTUANTE DE ADICIONAR (+) */}
      {activeTab === 'hoje' && (
        <button
          onClick={() => setModalMode('selection')}
          className="fixed bottom-24 right-6 bg-blue-600 text-white p-4 rounded-full shadow-lg shadow-blue-300 hover:bg-blue-700 hover:scale-105 transition-all z-20"
        >
          <Plus className="w-8 h-8" />
        </button>
      )}

      {modalMode === 'selection' && (
        <div className="fixed inset-0 bg-black/60 flex items-center justify-center z-50 p-4 animate-in fade-in">
          <div className="bg-white w-full max-w-sm rounded-3xl p-6 shadow-2xl relative">
            <button onClick={() => setModalMode('none')} className="absolute top-4 right-4 p-2 bg-gray-50 rounded-full text-gray-400 hover:bg-gray-100"><X className="w-5 h-5" /></button>
            <h3 className="text-center text-lg font-bold text-gray-700 mb-6">O que deseja adicionar?</h3>
            <div className="grid grid-cols-2 gap-4">
              <button onClick={() => setModalMode('medication')} className="flex flex-col items-center gap-3 p-4 rounded-2xl bg-blue-50 border-2 border-transparent hover:border-blue-500 hover:bg-blue-100 transition-all text-blue-700"><div className="bg-white p-3 rounded-full shadow-sm"><Pill className="w-8 h-8" /></div><span className="font-bold">Medicamento</span></button>
              <button onClick={() => setModalMode('appointment')} className="flex flex-col items-center gap-3 p-4 rounded-2xl bg-purple-50 border-2 border-transparent hover:border-purple-500 hover:bg-purple-100 transition-all text-purple-700"><div className="bg-white p-3 rounded-full shadow-sm"><Stethoscope className="w-8 h-8" /></div><span className="font-bold">Consulta</span></button>
            </div>
          </div>
        </div>
      )}

      {modalMode === 'medication' && <AddMedicationForm onClose={() => setModalMode('none')} onSave={adicionarMedicamento} />}
      {modalMode === 'appointment' && <AddAppointmentForm onClose={() => setModalMode('none')} onSave={adicionarConsulta} />}

      <nav className="fixed bottom-0 w-full bg-white border-t border-gray-200 py-3 px-6 flex justify-between items-center z-10">
        <button onClick={() => setActiveTab('hoje')} className={`flex flex-col items-center gap-1 transition-colors ${activeTab === 'hoje' ? 'text-blue-600' : 'text-gray-400 hover:text-gray-600'}`}><Pill className="w-6 h-6" /><span className="text-xs font-medium">Hoje</span></button>
        <button onClick={() => setActiveTab('agenda')} className={`flex flex-col items-center gap-1 transition-colors ${activeTab === 'agenda' ? 'text-blue-600' : 'text-gray-400 hover:text-gray-600'}`}><Calendar className="w-6 h-6" /><span className="text-xs font-medium">Agenda</span></button>
        <button onClick={() => setActiveTab('perfil')} className={`flex flex-col items-center gap-1 transition-colors ${activeTab === 'perfil' ? 'text-blue-600' : 'text-gray-400 hover:text-gray-600'}`}><User className="w-6 h-6" /><span className="text-xs font-medium">Perfil</span></button>
      </nav>
    </div>
  );
};

export default HealthDashboard;