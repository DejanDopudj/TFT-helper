/** @type {import('tailwindcss').Config} */
export default {
  mode: 'jit',
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx,vue}",
  ],
  theme: {
    extend: {
      colors: {
        "dark": "#13181B",
        "dork": "#2F4858",
        "medium": "#8199a0",
        "light": "#FDFFFC",
        "primary": "#ffcf36",
        "secondary": "#00A8BF",
      },
      spacing: {
        "13": "3.25rem",
        "18": "4.50rem",
        "22": "5.50rem",
        "23": "5.75rem",
        "76": "19rem",
        "88": "22rem",
        "100": "25rem",
        "108": "27rem",
        "120": "30rem",
        "140": "35rem",
        "160": "40rem",
      }
    },
  },
  plugins: [],
}
