import { useState, useEffect } from 'react';
import { motion } from 'motion/react';
import { UserPlus, Search, Edit2, Trash2 } from 'lucide-react';

export default function Students() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({ student_id: '', name: '', gender: 'Male', dob: '', department: '', semester: 1, email: '', phone: '' });

  const fetchStudents = () => {
    setLoading(true);
    fetch('/api/students')
      .then(res => res.json())
      .then(data => { setStudents(data); setLoading(false); })
      .catch(console.error);
  };

  useEffect(() => { fetchStudents(); }, []);

  const handleAdd = async (e: React.FormEvent) => {
    e.preventDefault();
    await fetch('/api/students', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData)
    });
    fetchStudents();
    setFormData({ student_id: '', name: '', gender: 'Male', dob: '', department: '', semester: 1, email: '', phone: '' });
  };

  const handleDelete = async (id: string) => {
    if(confirm('Delete student?')) {
      await fetch(`/api/students/${id}`, { method: 'DELETE' });
      fetchStudents();
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-end">
        <div>
          <h2 className="text-3xl font-bold text-slate-100">Manage Students</h2>
          <p className="text-slate-400 mt-1">Add, update, or remove student records.</p>
        </div>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <motion.div 
          initial={{ opacity: 0, x: -20 }}
          animate={{ opacity: 1, x: 0 }}
          className="glass p-6 rounded-2xl border border-slate-700/50 h-fit"
        >
          <h3 className="text-lg font-bold text-slate-100 mb-6 flex items-center">
            <UserPlus className="h-5 w-5 mr-2 text-primary" />
            Add New Student
          </h3>
          <form onSubmit={handleAdd} className="space-y-4">
            <div>
              <label className="block text-sm text-slate-400 mb-1">Student ID</label>
              <input type="text" className="input-field text-sm" value={formData.student_id} onChange={e => setFormData({...formData, student_id: e.target.value})} required />
            </div>
            <div>
              <label className="block text-sm text-slate-400 mb-1">Name</label>
              <input type="text" className="input-field text-sm" value={formData.name} onChange={e => setFormData({...formData, name: e.target.value})} required />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm text-slate-400 mb-1">Gender</label>
                <select className="input-field text-sm" value={formData.gender} onChange={e => setFormData({...formData, gender: e.target.value})}>
                  <option>Male</option>
                  <option>Female</option>
                  <option>Other</option>
                </select>
              </div>
              <div>
                <label className="block text-sm text-slate-400 mb-1">DOB</label>
                <input type="date" className="input-field text-sm" value={formData.dob} onChange={e => setFormData({...formData, dob: e.target.value})} />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm text-slate-400 mb-1">Dept</label>
                <input type="text" className="input-field text-sm" value={formData.department} onChange={e => setFormData({...formData, department: e.target.value})} />
              </div>
              <div>
                <label className="block text-sm text-slate-400 mb-1">Semester</label>
                <input type="number" className="input-field text-sm" value={formData.semester} onChange={e => setFormData({...formData, semester: parseInt(e.target.value)})} />
              </div>
            </div>
            <div>
              <label className="block text-sm text-slate-400 mb-1">Email</label>
              <input type="email" className="input-field text-sm" value={formData.email} onChange={e => setFormData({...formData, email: e.target.value})} />
            </div>
            <button type="submit" className="w-full btn-primary mt-2">Save Student</button>
          </form>
        </motion.div>

        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="lg:col-span-2 glass rounded-2xl border border-slate-700/50 overflow-hidden flex flex-col"
        >
          <div className="p-4 border-b border-slate-700/50 flex justify-between items-center bg-slate-800/30">
            <h3 className="font-bold text-slate-100">Student Directory</h3>
            <div className="relative">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              <input type="text" placeholder="Search..." className="pl-9 pr-4 py-1.5 bg-slate-900 border border-slate-700 rounded-lg text-sm focus:outline-none focus:border-primary text-slate-200" />
            </div>
          </div>
          <div className="overflow-x-auto flex-1">
            <table className="w-full text-sm text-left">
              <thead className="text-xs text-slate-400 uppercase bg-slate-800/50">
                <tr>
                  <th className="px-6 py-3">ID</th>
                  <th className="px-6 py-3">Name</th>
                  <th className="px-6 py-3">Dept</th>
                  <th className="px-6 py-3">Sem</th>
                  <th className="px-6 py-3 text-right">Actions</th>
                </tr>
              </thead>
              <tbody>
                {loading ? (
                  <tr><td colSpan={5} className="px-6 py-4 text-center text-slate-500">Loading...</td></tr>
                ) : students.map((s: any) => (
                  <tr key={s.student_id} className="border-b border-slate-800/50 hover:bg-slate-800/80 transition-colors">
                    <td className="px-6 py-4 font-medium text-slate-200">{s.student_id}</td>
                    <td className="px-6 py-4">{s.name}</td>
                    <td className="px-6 py-4">
                      <span className="px-2.5 py-1 rounded-full bg-blue-500/10 text-blue-400 border border-blue-500/20 text-xs">
                        {s.department}
                      </span>
                    </td>
                    <td className="px-6 py-4">{s.semester}</td>
                    <td className="px-6 py-4 text-right">
                      <button className="text-blue-400 hover:text-blue-300 mr-3 transition-colors"><Edit2 className="h-4 w-4" /></button>
                      <button onClick={() => handleDelete(s.student_id)} className="text-red-400 hover:text-red-300 transition-colors"><Trash2 className="h-4 w-4" /></button>
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
