/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx,vue}",
  ],
  theme: {
    extend: {
      colors: {
        "dark": "#13181B",
        "medium": "#2F4858",
        "light": "#FDFFFC",
        "primary": "#ffcf36",
        "secondary": "#00A8BF",
      },
      spacing: {
        "76": "19rem",
      }
    },
  },
  plugins: [],
}
