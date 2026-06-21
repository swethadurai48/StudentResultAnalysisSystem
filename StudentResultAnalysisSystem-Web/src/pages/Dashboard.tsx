import { useEffect, useState } from 'react';
import { Users, BookOpen, GraduationCap, TrendingUp } from 'lucide-react';
import { motion } from 'motion/react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts';

export default function Dashboard() {
  const [data, setData] = useState<any>(null);

  useEffect(() => {
    fetch('/api/analysis')
      .then(res => res.json())
      .then(setData)
      .catch(console.error);
  }, []);

  if (!data) return <div className="flex items-center justify-center h-full text-slate-400">Loading dashboard data...</div>;

  const COLORS = ['#3b82f6', '#8b5cf6', '#ec4899', '#10b981', '#f59e0b', '#ef4444'];

  const stats = [
    { title: 'Total Students', value: data.totalStudents, icon: Users, color: 'text-blue-400', bg: 'bg-blue-400/10', border: 'border-blue-400/20' },
    { title: 'Total Subjects', value: data.totalSubjects, icon: BookOpen, color: 'text-purple-400', bg: 'bg-purple-400/10', border: 'border-purple-400/20' },
    { title: 'Pass Percentage', value: `${data.passPercentage}%`, icon: GraduationCap, color: 'text-green-400', bg: 'bg-green-400/10', border: 'border-green-400/20' },
    { title: 'Highest Score', value: 'Top', icon: TrendingUp, color: 'text-pink-400', bg: 'bg-pink-400/10', border: 'border-pink-400/20' },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold text-slate-100">Dashboard Overview</h2>
        <p className="text-slate-400 mt-1">Here's the latest academic performance analysis.</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, i) => (
          <motion.div 
            key={i}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className={`glass p-6 rounded-2xl border ${stat.border} flex items-center space-x-4 hover:bg-slate-800/40 transition-colors`}
          >
            <div className={`p-4 rounded-xl ${stat.bg}`}>
              <stat.icon className={`h-8 w-8 ${stat.color}`} />
            </div>
            <div>
              <p className="text-sm text-slate-400 font-medium uppercase tracking-wider">{stat.title}</p>
              <h3 className="text-3xl font-bold text-slate-100 mt-1">{stat.value}</h3>
            </div>
          </motion.div>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mt-6">
        <motion.div 
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ delay: 0.4 }}
          className="glass p-6 rounded-2xl border border-slate-700/50"
        >
          <h3 className="text-lg font-bold text-slate-100 mb-6 flex items-center">
            <span className="w-2 h-6 bg-primary rounded-full mr-3"></span>
            Subject Performance (Avg Marks)
          </h3>
          <div className="h-80">
            <ResponsiveContainer width="100%" height="100%">
              <BarChart data={data.subjectPerformance} margin={{ top: 20, right: 30, left: 0, bottom: 5 }}>
                <XAxis dataKey="subject_id" stroke="#94a3b8" tickLine={false} axisLine={false} />
                <YAxis stroke="#94a3b8" tickLine={false} axisLine={false} />
                <Tooltip 
                  cursor={{fill: 'rgba(255,255,255,0.05)'}}
                  contentStyle={{ backgroundColor: '#1e293b', borderColor: '#334155', color: '#f8fafc', borderRadius: '0.5rem' }}
                  itemStyle={{ color: '#3b82f6', fontWeight: 'bold' }}
                />
                <Bar dataKey="avgMarks" fill="#3b82f6" radius={[6, 6, 0, 0]} />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </motion.div>

        <motion.div 
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ delay: 0.5 }}
          className="glass p-6 rounded-2xl border border-slate-700/50"
        >
          <h3 className="text-lg font-bold text-slate-100 mb-6 flex items-center">
            <span className="w-2 h-6 bg-accent rounded-full mr-3"></span>
            Grade Distribution
          </h3>
          <div className="h-80">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={data.gradeDistribution}
                  cx="50%"
                  cy="50%"
                  innerRadius={70}
                  outerRadius={110}
                  paddingAngle={5}
                  dataKey="value"
                  stroke="none"
                >
                  {data.gradeDistribution.map((_: any, index: number) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip 
                  contentStyle={{ backgroundColor: '#1e293b', borderColor: '#334155', color: '#f8fafc', borderRadius: '0.5rem' }}
                />
              </PieChart>
            </ResponsiveContainer>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
