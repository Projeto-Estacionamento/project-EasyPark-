import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      'react-native': 'react-native-web',
      '@react-native-async-storage/async-storage': '@react-native-community/async-storage',
    },
  },
});