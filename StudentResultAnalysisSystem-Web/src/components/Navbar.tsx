import { Bell, Search, UserCircle } from 'lucide-react';

export default function Navbar() {
  return (
    <header className="h-16 glass border-b border-slate-700/50 flex items-center justify-between px-6 z-10 relative">
      <div className="flex items-center bg-slate-800/50 border border-slate-700 rounded-full px-4 py-1.5 w-64 focus-within:ring-2 focus-within:ring-primary/50 transition-all">
        <Search className="h-4 w-4 text-slate-400 mr-2" />
        <input 
          type="text" 
          placeholder="Search..." 
          className="bg-transparent border-none focus:outline-none text-sm w-full text-slate-200"
        />
      </div>
      
      <div className="flex items-center space-x-4">
        <button className="p-2 rounded-full hover:bg-slate-800 transition-colors relative">
          <Bell className="h-5 w-5 text-slate-300" />
          <span className="absolute top-1 right-1 h-2 w-2 rounded-full bg-red-500 border border-slate-900"></span>
        </button>
        <div className="flex items-center border-l border-slate-700 pl-4 ml-2">
          <div className="text-right mr-3 hidden sm:block">
            <p className="text-sm font-medium text-slate-200">Admin User</p>
            <p className="text-xs text-slate-400">admin@sras.edu</p>
          </div>
          <UserCircle className="h-9 w-9 text-slate-300" />
        </div>
      </div>
    </header>
  );
}
