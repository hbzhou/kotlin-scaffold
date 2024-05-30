import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { dirname, resolve } from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: "jdom",
    exclude: ["**/node_modules/**"],
    threads: false,
    setupFiles: "./vitest-setup.ts",
    coverage: {
      reporter: ["text", "lcov"],
    },
    reporters: ["verbose", "junit", "json", "vitest-sonar-reporter"],
    outputFile: {
      json: "reports/json-report.json",
      junit: "test_results/unit_test_report.xml",
      "vitest-sonar-reporter": "reports/sonar-report.xml",
    },
  },
  resolve: {
    alias: [{ find: "@", replacement: resolve(__dirname, "src") }],
    mainFields: ["module", "jsnext:main", "jsnext"],
  },
});
