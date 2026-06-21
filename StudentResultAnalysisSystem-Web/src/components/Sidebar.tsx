import { Link, useLocation } from 'react-router-dom';
import { LayoutDashboard, Users, BookOpen, GraduationCap, FileText, LogOut } from 'lucide-react';
import { motion } from 'motion/react';

export default function Sidebar() {
  const location = useLocation();

  const links = [
    { name: 'Dashboard', path: '/dashboard', icon: LayoutDashboard },
    { name: 'Students', path: '/students', icon: Users },
    { name: 'Subjects', path: '/subjects', icon: BookOpen },
    { name: 'Marks Entry', path: '/marks', icon: GraduationCap },
    { name: 'Reports', path: '/reports', icon: FileText },
  ];

  return (
    <div className="w-64 glass border-r border-slate-700/50 flex flex-col justify-between h-full relative z-20">
      <div>
        <div className="h-16 flex items-center px-6 border-b border-slate-700/50">
          <GraduationCap className="h-8 w-8 text-primary mr-3" />
          <h1 className="text-xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-primary to-accent">
            SRAS Portal
          </h1>
        </div>
        <nav className="p-4 space-y-2 mt-4">
          {links.map((link) => {
            const isActive = location.pathname.startsWith(link.path);
            const Icon = link.icon;
            
            return (
              <Link key={link.path} to={link.path} className="block relative">
                {isActive && (
                  <motion.div
                    layoutId="active-nav"
                    className="absolute inset-0 bg-primary/10 border border-primary/20 rounded-lg"
                    initial={false}
                    transition={{ type: "spring", bounce: 0.2, duration: 0.6 }}
                  />
                )}
                <div className={`relative flex items-center px-4 py-3 rounded-lg transition-colors ${isActive ? 'text-primary' : 'text-slate-400 hover:text-slate-200 hover:bg-slate-800/50'}`}>
                  <Icon className="h-5 w-5 mr-3" />
                  <span className="font-medium">{link.name}</span>
                </div>
              </Link>
            );
          })}
        </nav>
      </div>
      <div className="p-4 border-t border-slate-700/50">
        <Link to="/login" className="flex items-center px-4 py-3 rounded-lg text-slate-400 hover:text-red-400 hover:bg-red-400/10 transition-colors">
          <LogOut className="h-5 w-5 mr-3" />
          <span className="font-medium">Logout</span>
        </Link>
      </div>
    </div>
  );
}
