import { useState, useEffect } from 'react';
import { motion } from 'motion/react';
import { GraduationCap, Search, Edit2, Trash2 } from 'lucide-react';

export default function Marks() {
  const [marks, setMarks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({ student_id: '', subject_id: '', internal_marks: 0, external_marks: 0 });

  const fetchMarks = () => {
    setLoading(true);
    fetch('/api/marks')
      .then(res => res.json())
      .then(data => { setMarks(data); setLoading(false); })
      .catch(console.error);
  };

  useEffect(() => { fetchMarks(); }, []);

  const handleAdd = async (e: React.FormEvent) => {
    e.preventDefault();
    await fetch('/api/marks', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    });
    fetchMarks();
    setFormData({ student_id: '', subject_id: '', internal_marks: 0, external_marks: 0 });
  };

  const handleDelete = async (id: number) => {
    if(confirm('Delete mark entry?')) {
      await fetch(`/api/marks/${id}`, { method: 'DELETE' });
      fetchMarks();
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-end">
        <div>
          <h2 className="text-3xl font-bold text-slate-100">Marks Entry</h2>
          <p className="text-slate-400 mt-1">Record and manage student examination marks.</p>
        </div>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <motion.div 
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          className="glass p-6 rounded-2xl border border-slate-700/50 h-fit"
        >
          <h3 className="text-lg font-bold text-slate-100 mb-6 flex items-center">
            <GraduationCap className="h-5 w-5 mr-2 text-primary" />
            Add Marks
          </h3>
          <form onSubmit={handleAdd} className="space-y-4">
            <div>
              <label className="block text-sm text-slate-400 mb-1">Student ID</label>
              <input type="text" className="input-field text-sm" value={formData.student_id} onChange={e => setFormData({...formData, student_id: e.target.value})} required />
            </div>
            <div>
              <label className="block text-sm text-slate-400 mb-1">Subject ID</label>
              <input type="text" className="input-field text-sm" value={formData.subject_id} onChange={e => setFormData({...formData, subject_id: e.target.value})} required />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm text-slate-400 mb-1">Internal Marks</label>
                <input type="number" step="0.01" className="input-field text-sm" value={formData.internal_marks} onChange={e => setFormData({...formData, internal_marks: parseFloat(e.target.value)})} required />
              </div>
              <div>
                <label className="block text-sm text-slate-400 mb-1">External Marks</label>
                <input type="number" step="0.01" className="input-field text-sm" value={formData.external_marks} onChange={e => setFormData({...formData, external_marks: parseFloat(e.target.value)})} required />
              </div>
            </div>
            <div className="p-3 mt-4 rounded-xl bg-slate-800/50 border border-slate-700 flex justify-between items-center shadow-inner">
              <span className="text-sm text-slate-400">Total Marks</span>
              <span className="font-bold text-primary text-2xl">
                {(formData.internal_marks + formData.external_marks) || 0}
              </span>
            </div>
            <button type="submit" className="w-full btn-primary mt-4 flex items-center justify-center">
              Save Marks
            </button>
          </form>
        </motion.div>

        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="lg:col-span-2 glass rounded-2xl border border-slate-700/50 overflow-hidden flex flex-col"
        >
          <div className="p-4 border-b border-slate-700/50 flex justify-between items-center bg-slate-800/30">
            <h3 className="font-bold text-slate-100">Marks Directory</h3>
            <div className="relative">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              <input type="text" placeholder="Search..." className="pl-9 pr-4 py-1.5 bg-slate-900 border border-slate-700 rounded-lg text-sm focus:outline-none focus:border-primary text-slate-200" />
            </div>
          </div>
          <div className="overflow-x-auto flex-1">
            <table className="w-full text-sm text-left">
              <thead className="text-xs text-slate-400 uppercase bg-slate-800/50">
                <tr>
                  <th className="px-6 py-3">Student ID</th>
                  <th className="px-6 py-3">Subject ID</th>
                  <th className="px-6 py-3">Internal</th>
                  <th className="px-6 py-3">External</th>
                  <th className="px-6 py-3">Total</th>
                  <th className="px-6 py-3 text-right">Actions</th>
                </tr>
              </thead>
              <tbody>
                {loading ? (
                  <tr><td colSpan={6} className="px-6 py-4 text-center text-slate-500">Loading...</td></tr>
                ) : marks.map((m: any) => (
                  <tr key={m.mark_id} className="border-b border-slate-800/50 hover:bg-slate-800/80 transition-colors">
                    <td className="px-6 py-4 font-medium text-slate-200">{m.student_id}</td>
                    <td className="px-6 py-4">
                      <span className="px-2.5 py-1 rounded-md bg-purple-500/10 text-purple-400 border border-purple-500/20 font-mono text-xs">
                        {m.subject_id}
                      </span>
                    </td>
                    <td className="px-6 py-4">{m.internal_marks}</td>
                    <td className="px-6 py-4">{m.external_marks}</td>
                    <td className="px-6 py-4">
                      <span className={`px-2.5 py-1 rounded-full text-xs font-bold ${m.total_marks >= 50 ? 'bg-green-500/10 text-green-400 border border-green-500/20' : 'bg-red-500/10 text-red-400 border border-red-500/20'}`}>
                        {m.total_marks}
                      </span>
                    </td>
                    <td className="px-6 py-4 text-right">
                      <button className="text-blue-400 hover:text-blue-300 mr-3 transition-colors"><Edit2 className="h-4 w-4" /></button>
                      <button onClick={() => handleDelete(m.mark_id)} className="text-red-400 hover:text-red-300 transition-colors"><Trash2 className="h-4 w-4" /></button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
