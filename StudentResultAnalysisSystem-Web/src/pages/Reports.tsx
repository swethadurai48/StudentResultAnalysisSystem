import { FileText, Download } from 'lucide-react';
import { motion } from 'motion/react';

export default function Reports() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-3xl font-bold text-slate-100">Reports Generation</h2>
        <p className="text-slate-400 mt-1">Export academic data as PDF and Excel reports.</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl">
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="glass p-8 rounded-2xl border border-slate-700/50 relative overflow-hidden group"
        >
          <div className="absolute top-0 right-0 p-6 opacity-10 group-hover:opacity-20 transition-opacity">
            <FileText className="h-32 w-32 text-green-500" />
          </div>
          <h3 className="text-2xl font-bold text-slate-100 mb-2">Class Report</h3>
          <p className="text-slate-400 mb-8 max-w-xs">Generate a comprehensive Excel sheet containing all student records and aggregates.</p>
          
          <button className="flex items-center justify-center w-full py-3 bg-green-500 hover:bg-green-600 text-white rounded-xl font-medium transition-colors shadow-lg shadow-green-500/20">
            <Download className="h-5 w-5 mr-2" />
            Download Excel Report
          </button>
        </motion.div>

        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
          className="glass p-8 rounded-2xl border border-slate-700/50 relative overflow-hidden group"
        >
          <div className="absolute top-0 right-0 p-6 opacity-10 group-hover:opacity-20 transition-opacity">
            <FileText className="h-32 w-32 text-red-500" />
          </div>
          <h3 className="text-2xl font-bold text-slate-100 mb-2">Student Report</h3>
          <p className="text-slate-400 mb-6 max-w-xs">Generate a detailed PDF mark sheet for an individual student.</p>
          
          <div className="space-y-4 relative z-10">
            <div>
              <label className="block text-sm text-slate-400 mb-1">Student ID</label>
              <input type="text" className="input-field text-sm w-full" placeholder="e.g. S001" />
            </div>
            <button className="flex items-center justify-center w-full py-3 bg-red-500 hover:bg-red-600 text-white rounded-xl font-medium transition-colors shadow-lg shadow-red-500/20">
              <Download className="h-5 w-5 mr-2" />
              Download PDF Report
            </button>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
